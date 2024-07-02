package car_rent.service;

import car_rent.model.Brand;

import java.util.List;

public interface BrandService {
    List<Brand> getAll();
    Brand getOne(Integer id);
    Brand create(Brand request);
    Brand update(Integer id, Brand request);
    void delete(Integer id);
}
