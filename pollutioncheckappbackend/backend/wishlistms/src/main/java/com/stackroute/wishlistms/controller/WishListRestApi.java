package com.stackroute.wishlistms.controller;

import com.stackroute.wishlistms.dto.AddToWatchList;
import com.stackroute.wishlistms.dto.WishListCityDeatils;
import com.stackroute.wishlistms.dto.RemoveFromWatchList;
import com.stackroute.wishlistms.exceptions.CityInfoNotFoundException;
import com.stackroute.wishlistms.service.IWishListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller API for wishlist module
 *
 * @author DIVYANSHU KUMAR UPADHYAY
 */
@RequestMapping("/wishlist")
@RestController
public class WishListRestApi {

    @Autowired
    private IWishListService service;

    /**
     * Provides API to handel get request for obtaining List of Cities added in mongo database,
     *
     * @return List<WishListCityDeatils>
     * @throws Exception
     * @PathVariable id String,
     */
    @GetMapping("/byid/{id}")
    public List<WishListCityDeatils> findAll(@PathVariable String id) throws Exception {

        List<WishListCityDeatils> response = service.listWatchListByUserName(id);
        return response;
    }

    /**
     * Provides API to handel post request for adding Cities in mongo database,
     *
     * @return WishListCityDeatils
     * @throws Exception
     * @RequestBody requestData AddToWatchList,
     */

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public WishListCityDeatils add(@RequestBody AddToWatchList requestData) throws Exception {
        WishListCityDeatils response = service.addToWishList(requestData);
        return response;
    }

    /**
     * Provides API to handel delete request for deleting Cities from mongo database,
     *
     * @return WishListCityDeatils
     * @throws CityInfoNotFoundException
     * @RequestBody requestData RemoveFromWatchList,
     */
    @DeleteMapping("/delete")
    public void remove(@RequestBody RemoveFromWatchList requestData) throws CityInfoNotFoundException {

        service.remove(requestData);

    }

}
