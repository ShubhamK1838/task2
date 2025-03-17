package com.zestindia.t2.service;

import com.zestindia.t2.entity.Category;
import com.zestindia.t2.exception.custom.CategoryNotFoundException;
import com.zestindia.t2.repository.CategoryRepository;
import com.zestindia.t2.service.impl.CategoryServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceTest {

    private Category category;

    @Mock
    private CategoryRepository repository;

    @InjectMocks
    private CategoryServiceImpl service;

    @BeforeEach
    void initCategory() {
        category = Category.builder().id("ID").name("NAME").build();
    }

    @Test
    void saveCategoryTest() {
        when(repository.save(any(Category.class))).thenReturn(category);

        Category result = service.save(category);

        assertNotNull(result);
        assertNotNull(result.getId());
    }


    @Test
    void updateCategoryIfExists()
    {
       var old= Category.builder().name("OLDNAME").build();
        when(repository.save(any(Category.class))).thenReturn(category) ;
        when(repository.findById(anyString())).thenReturn(Optional.of(old));

        service.update(category);
        assertEquals(old.getName(), category.getName());

    }
    @Test
    void deleteCategoryTestIfCategoryExists() {
        when(repository.findById(anyString())).thenReturn(Optional.of(category));
        doNothing().when(repository).delete(category);

        service.delete(category.getId());

        verify(repository, times(1)).delete(category);
    }

    @Test
    void deleteCategoryIfCategoryNotExistsTest() {
        when(repository.findById(anyString())).thenReturn(Optional.empty());

        assertThrows(CategoryNotFoundException.class, () -> service.delete("InvalidID"));
    }

    @Test
    void getCategoryById() {
        when(repository.findById(anyString())).thenReturn(Optional.of(category));

        var optional = service.getCategory(category.getId());

        assertTrue(optional.isPresent());
        assertNotNull(optional.orElse(null));
    }

    @Test
    void getCategoryByIdIfCategoryNotPresent() {
        when(repository.findById(anyString())).thenReturn(Optional.empty());

        assertTrue(service.getCategory("InvalidID").isEmpty());
    }

    @Test
    void getAllCategory() {
        when(repository.findAll()).thenReturn(List.of(category));

        assertNotNull(service.getAll());
    }

    @Test
    void getCategoryByName() {
        when(repository.findByName(anyString())).thenReturn(Optional.of(category));

        assertNotNull(service.getByName("NAME"));
    }

    @Test
    void putCategoryByNameIfCategoryExistsTest()
    {
        Category result;
        when(repository.findByName(anyString())).thenReturn(Optional.of(category));

        assertNotNull(result=service.putCategoryByName("NAME") ) ;
        assertEquals(result.getId(), category.getId());
    }
    @Test
    void putCategoryByNameIfCategoryNotExistsTest()
    {
        Category result;
        when(repository.findByName(anyString())).thenReturn(Optional.empty());
        when(repository.save(any(Category.class))).thenReturn(category);

        assertNotNull(result=service.putCategoryByName("NAME") ) ;
        assertEquals(result.getId(), category.getId());
    }
}
