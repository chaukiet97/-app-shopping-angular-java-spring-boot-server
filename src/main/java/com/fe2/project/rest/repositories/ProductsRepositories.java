package com.fe2.project.rest.repositories;

import java.util.List;

import javax.transaction.Transactional;

import com.fe2.project.rest.entities.Products;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProductsRepositories extends PagingAndSortingRepository<Products, Integer> {
        List<Products> findByName(String name);

        List<Products> findAll();

        @Modifying
        @Query(value = "SELECT p.* , pg.link as'parent_link', pg.name AS'name_group', b.name as'name_brand', mi.name as 'name_made' "
                        + "FROM `products` AS p LEFT JOIN `products_group` AS pg ON pg.id = p.group_id LEFT JOIN `brand` as b ON b.id = p.`brand_id` "
                        + "LEFT JOIN `made_in` AS mi ON mi.id = p.`made_in_id`", nativeQuery = true)
        List<Object[]> findAllProduct();

        @Modifying
        @Query(value = "UPDATE `products` SET `brand_id`=?1,`description`=?2,`detail`=?3,`images`=?4,"
                        + "`link`=?5,`list_images`=?6,`made_in_id`=?7,`name`=?8,`price`=?9,`status`=?10,"
                        + "`count`=?11,`group_id`=?12,`price_sale`=?13 WHERE `id`=?14", nativeQuery = true)
        @Transactional
        void updateProduct(Integer brand_id, String description, String detail, String images, String link,
                        String list_images, Integer made_in_id, String name, Double price, Integer status,
                        Integer count, Integer group_id, Double price_sale, Integer id);
}
