package com.MIW.Cohort5.Clups.controller;

import com.MIW.Cohort5.Clups.dtos.ProductDto;
import com.MIW.Cohort5.Clups.dtos.stateKeeper.ProductPageStateKeeper;
import com.MIW.Cohort5.Clups.services.CategoryService;
import com.MIW.Cohort5.Clups.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/**
 * Johnnie Meijer
 * j.j.meijer@st.hanze.nl
 *
 * This controller arranges all views and actions for the Products page
 */

@Controller
@SessionAttributes("productPageStateKeeper")
public class ProductPageController {

    public final ProductService productService;
    public final CategoryService categoryService;

    @Autowired
    public ProductPageController(ProductService products, CategoryService categories) {
        this.productService = products;
        this.categoryService = categories;
    }

    @ModelAttribute("productPageStateKeeper")
    public ProductPageStateKeeper productPageStateKeeper() {
        return new ProductPageStateKeeper();
    }

    @GetMapping({"/products"})
    protected String showPage(
            Model model,
            @ModelAttribute("productPageStateKeeper") ProductPageStateKeeper productPageStateKeeper) {

        model.addAttribute("allCategories", categoryService.getAll());
        model.addAttribute("allProductsByCategory",
                productService.getProductsByCategory(productPageStateKeeper.getCategoryName()));
        model.addAttribute("formState", productPageStateKeeper.isShowForm());


        if (productPageStateKeeper.getCurrentProduct() == null) {
            productPageStateKeeper.setCurrentProduct(new ProductDto());
        }

        model.addAttribute("product", productPageStateKeeper.getCurrentProduct());
        model.addAttribute("selectedPage", "productPage");
        model.addAttribute("category", productPageStateKeeper.getCurrentCategory());

        return "productEditor";
    }

    @GetMapping({"/products/selectCategory/{categoryName}"})
    protected String showProductsByCategory(
            @PathVariable("categoryName") String categoryName,
            @SessionAttribute("productPageStateKeeper") ProductPageStateKeeper productPageStateKeeper) {

        productPageStateKeeper.setCategoryName(categoryName);

        return "redirect:/products";
    }

    @GetMapping({"/products/addNew"})
    protected String addNewProduct(
            Model model,
            @SessionAttribute("productPageStateKeeper") ProductPageStateKeeper productPageStateKeeper) {

        model.addAttribute("allCategoryNames", categoryService.getAll());

        productPageStateKeeper.clearCurrentProduct();

        showForm(productPageStateKeeper);

        return "redirect:/products";
    }

    @PostMapping({"/products/addNew"})
    protected String saveNewProduct
            (@ModelAttribute("product") ProductDto productDto, BindingResult result,
             @SessionAttribute("productPageStateKeeper") ProductPageStateKeeper productPageStateKeeper) {

        if (!result.hasErrors()) {
            productPageStateKeeper.setCurrentProduct(productDto);

            productService.saveProduct(productPageStateKeeper.getCurrentProduct());

            showCategoryForChangedProduct(productDto.getCategoryName(), productPageStateKeeper);

            clearForm(productPageStateKeeper);
        }

        return "redirect:/products";
    }

    private void showCategoryForChangedProduct(
            String categoryName,
            @SessionAttribute("productPageStateKeeper") ProductPageStateKeeper productPageStateKeeper) {

        // save category of added product to statekeeper, so you get to see the product immediately in its category
        productPageStateKeeper.setCategoryName(categoryName);
    }

    private void clearForm(ProductPageStateKeeper productPageStateKeeper) {
        productPageStateKeeper.clearCurrentProduct();
        productPageStateKeeper.setShowForm(false);
    }

    @GetMapping("/products/{productCode}")
    protected String editProduct(
            @PathVariable("productCode") String productCodeString,
            @SessionAttribute("productPageStateKeeper") ProductPageStateKeeper productPageStateKeeper) {

        ProductDto selectedProduct = productService.findDtoByCode(Integer.parseInt(productCodeString));
        productPageStateKeeper.setCurrentProduct(selectedProduct);

        showForm(productPageStateKeeper);

        return "redirect:/products";
    }

    @PostMapping("/products/edit")
    protected String saveExistingProduct(
            @ModelAttribute("product") ProductDto productDto,
            BindingResult result,
            @SessionAttribute("productPageStateKeeper") ProductPageStateKeeper productPageStateKeeper) {

        if (!result.hasErrors()) {
            productPageStateKeeper.setCurrentProduct(productDto);

            productService.saveProduct(productPageStateKeeper.getCurrentProduct());

            showCategoryForChangedProduct(productDto.getCategoryName(), productPageStateKeeper);

            clearForm(productPageStateKeeper);
        }

        return "redirect:/products";
    }

    @GetMapping("/products/delete")
    protected  String deleteProduct(@SessionAttribute("productPageStateKeeper") ProductPageStateKeeper productPageStateKeeper) {
        productService.deleteProduct(productPageStateKeeper.getCurrentProduct());

        showCategoryForChangedProduct(productPageStateKeeper.getCurrentProduct().getCategoryName(), productPageStateKeeper);

        clearForm(productPageStateKeeper);

        return "redirect:/products";
    }

    @GetMapping("/products/cancel")
    protected String cancelProductForm(
            @SessionAttribute("productPageStateKeeper") ProductPageStateKeeper productPageStateKeeper) {

        productPageStateKeeper.setShowForm(false);

        return "redirect:/products";
    }

    private void showForm(@SessionAttribute("productPageStateKeeper") ProductPageStateKeeper productPageStateKeeper) {
        if (!productPageStateKeeper.isShowForm()) {
            productPageStateKeeper.setShowForm(true);
        }
    }

}
