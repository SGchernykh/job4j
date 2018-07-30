package ru.todo.lucene.index;

/**
 * InMemoryLuceneIndex.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.*;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class InMemoryLuceneIndex {
    private Directory memoryIndex;
    private StandardAnalyzer analyzer;

    public InMemoryLuceneIndex(Directory memoryIndex, StandardAnalyzer analyzer) {
        super();
        this.memoryIndex = memoryIndex;
        this.analyzer = analyzer;
    }

    public void indexDocument(String description) {
        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(analyzer);
        try {
            IndexWriter writter = new IndexWriter(memoryIndex, indexWriterConfig);
            Document document = new Document();
            document.add(new TextField("description", description, Field.Store.YES));
            writter.addDocument(document);
            writter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Document> searchIndex(Query query) {
        try {
            IndexReader indexReader = DirectoryReader.open(memoryIndex);
            IndexSearcher searcher = new IndexSearcher(indexReader);
            TopDocs topDocs = searcher.search(query, 10);
            List<Document> documents = new ArrayList<>();
            for (ScoreDoc scoreDoc : topDocs.scoreDocs) {
                documents.add(searcher.doc(scoreDoc.doc));
            }
            return documents;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
