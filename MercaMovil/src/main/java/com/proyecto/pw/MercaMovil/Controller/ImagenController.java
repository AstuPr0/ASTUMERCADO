package com.proyecto.pw.MercaMovil.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.ServletContextAware;

import com.proyecto.pw.MercaMovil.DTO.ProductoDTO;
import com.proyecto.pw.MercaMovil.Service.ProductoService;

import javax.servlet.ServletContext;

import java.io.IOException;
import java.io.InputStream;

@Controller
@RequestMapping("/product-image")
public class ImagenController implements ServletContextAware {

    private ServletContext servletContext;

    @Autowired
    private ProductoService productoService;

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> obtenerImagen(@PathVariable Long id) {
        ProductoDTO producto = productoService.obtenerProductoPorId(id);

        byte[] imagenBytes;
        if (producto != null && producto.getImage() != null) {
            imagenBytes = producto.getImage();
        } else {
            imagenBytes = obtenerImagenPredefinida();
        }

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", servletContext.getMimeType("imagen.jpg"));

        return new ResponseEntity<>(imagenBytes, headers, HttpStatus.OK);
    }

    private byte[] obtenerImagenPredefinida() {
        try {
            InputStream inputStream = servletContext.getResourceAsStream("/resources/img/card-img-not-fund.png");
            return inputStream.readAllBytes();
        } catch (IOException e) {
            e.printStackTrace();
            return new byte[0];
        }
    }
}