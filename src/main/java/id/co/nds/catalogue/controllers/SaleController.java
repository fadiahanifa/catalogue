package id.co.nds.catalogue.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.co.nds.catalogue.entities.SaleEntity;
import id.co.nds.catalogue.exceptions.ClientException;
import id.co.nds.catalogue.models.ResponseModel;
import id.co.nds.catalogue.models.SaleModel;
import id.co.nds.catalogue.services.SaleService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/sale")
public class SaleController {
    @Autowired
    public SaleService saleService;

    @PostMapping(value = "/add")
    public ResponseEntity<ResponseModel> postUserController(@RequestBody SaleModel saleModel) {
        try {
            // Request
            SaleEntity sale = saleService.doSale(saleModel);

            // Response
            ResponseModel response = new ResponseModel();
            response.setMsg("New sale is successfully added");
            response.setData(sale);

            return ResponseEntity.ok(response);
            }
        catch (ClientException e) {
            ResponseModel response = new ResponseModel();
            response.setMsg(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
        catch (Exception e) {
            ResponseModel response = new ResponseModel();
            response.setMsg("Sorry, there is a failure on our server");
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(response);
        }
    }
    
}
