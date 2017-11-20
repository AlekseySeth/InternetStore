package service;

import dao.CategoryDao;
import entity.product.Category;
import entity.product.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * @author a.shestovsky
 */
public class CatalogService {

    public List<Category> getParentCategories() {
        List<Category> parentCategories = new ArrayList<>();
        List<Category> allCategories = getAllCategories();

        for (Category current : allCategories) {
            if (current.getCategory() == null) {
                parentCategories.add(current);
            }
        }

        return parentCategories;
    }

    public List<Category> getCategoriesByParent(Category category) {
        List<Category> childCategories = new ArrayList<>();
        List<Category> allCategories = getAllCategories();

        for (Category current : allCategories) {
            if (current.getCategory().equals(category)) {
                childCategories.add(current);
            }
        }

        return childCategories;
    }

    public List<Category> getAllCategories() {

        return null;
    }

    public Category addNewCategory(Category category) {
        CategoryDao categoryDao = CategoryDao.newInstance();
        return categoryDao.save(category);
    }

    public Category getCategoryByName() {

        return null;
    }

    public Category getCategoryById(Long id) {
        CategoryDao categoryDao = CategoryDao.newInstance();
        return categoryDao.get(id);
    }

    public List<Product> getProductsByCategory(Category category) {

        return null;
    }

    public List<Product> getAllProducts() {

        return null;
    }

    public Product getProductByName() {

        return null;
    }


    public Product getProductById() {

        return null;
    }

    public Product addNewProduct() {

        return null;
    }

    public Product updateProduct() {

        return null;
    }


}