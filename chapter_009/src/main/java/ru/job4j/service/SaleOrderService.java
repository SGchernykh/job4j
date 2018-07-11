package ru.job4j.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.job4j.models.SaleOrder;
import ru.job4j.repository.*;

import java.sql.Timestamp;
import java.util.List;

@Service
public class SaleOrderService {

    @Autowired
    private SaleOrderRepository saleOrderRepository;

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private ModelRepository modelRepository;

    @Autowired
    private CarBodyRepository carBodyRepository;

    @Autowired
    private TransmissionRepository transmissionRepository;

    @Autowired
    private EngineRepository engineRepository;

    @Autowired
    private DriveUnitRepository driveUnitRepository;

    @Autowired
    private CarService carService;

    @Autowired
    private CityService cityService;

    @Autowired
    private UserService userService;

    @Autowired
    private PhotoService photoService;

    public SaleOrder save(final SaleOrder saleOrder) {
        return this.saleOrderRepository.save(saleOrder);
    }

    public SaleOrder getById(final int id) {
        return this.saleOrderRepository.findById(id).get();
    }

    public List<SaleOrder> getAll() {
        return (List<SaleOrder>) this.saleOrderRepository.findAll();
    }

    public SaleOrder prepareAdvert(final ModelForFillingAdverts model, final MultipartFile file) {
        SaleOrder saleOrder = new SaleOrder();
        saleOrder.setAuthor(this.getUser());
        saleOrder.setTitle(model.getTitle());
        saleOrder.setSale(false);
        saleOrder.setDescription(model.getDescription());
        saleOrder.setPrice(Integer.parseInt(model.getPrice()));
        saleOrder.setCreated(new Timestamp(System.currentTimeMillis()));
        saleOrder.setPhoto(this.imageService.save(new WritePhotoToDisk().writePhotoToDisk(file)));
        saleOrder.setCar(this.carService.save(this.fillCar(model)));
        saleOrder.setCity(this.cityService.getByName(model.getCity()));
        return saleOrder;
    }
}
