package car_rent.service.impl;

import car_rent.model.Brand;
import car_rent.repository.BrandRepository;
import car_rent.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {
    private final BrandRepository brandRepository;

    @Override
    public List<Brand> getAll() {
        return brandRepository.findAll();
    }

    @Override
    public Brand getOne(Integer id) {
        return brandRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Brand id is not found"));
    }

    @Override
    public Brand create(Brand request) {
        return brandRepository.save(request);
    }

    @Override
    public Brand update(Integer id, Brand request) {
        Brand brand = getOne(id);
        brand.setName(request.getName());
        brandRepository.save(brand);
        return brand;
    }

    @Override
    public void delete(Integer id) {
        brandRepository.deleteById(id);
    }
}
