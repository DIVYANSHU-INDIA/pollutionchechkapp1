package com.stackroute.wishlistms.service;

import com.stackroute.wishlistms.repository.ICityInfoRepository;

import com.stackroute.wishlistms.dto.AddToWatchList;

import com.stackroute.wishlistms.dto.WishListCityDeatils;
import com.stackroute.wishlistms.dto.RemoveFromWatchList;
import com.stackroute.wishlistms.entity.CityInfo;
import com.stackroute.wishlistms.exceptions.CityInfoAlreadyExistsException;
import com.stackroute.wishlistms.exceptions.CityInfoNotFoundException;
import com.stackroute.wishlistms.util.WishlistCityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * service implementation for wishlist module
 *
 * @author DIVYANSHU KUMAR UPADHYAY
 */
@Service
public class WishListServiceImpl implements IWishListService {

    @Autowired
    private ICityInfoRepository repository;


    @Autowired
    private WishlistCityUtil util;

    public String generateId(String userID, String city, String state, String country) {
        String id = city + "-" + state + "-" + country + "-u-" + userID;
        return id;
    }

    /**
     * saves wislisted city in the mongo database,
     * if city already exists for the given username, city, state and country then throw CityInfoAlreadyExistsException
     *
     * @param requestData AddToWatchList
     * @return WishListCityDeatils
     * @throws CityInfoAlreadyExistsException if city already exists
     */
    @Override
    public WishListCityDeatils addToWishList(AddToWatchList requestData) throws CityInfoAlreadyExistsException {
        Optional<CityInfo> optional = repository.findByUserNameAndCityAndStateAndCountry(requestData.getUserName(), requestData.getCity(), requestData.getState(), requestData.getCountry());
        if (optional.isPresent()) {
            throw new CityInfoAlreadyExistsException("City Info is already present in the WishList!");

        }
        CityInfo cityInfo;
        cityInfo = util.toCityInfo(requestData);
        cityInfo.setId(generateId(requestData.getUserName(), requestData.getCity(), requestData.getState(), requestData.getCountry()));

        cityInfo = repository.save(cityInfo);

        WishListCityDeatils desired = util.toCityDetails(cityInfo);
        return desired;
    }

    /**
     * delete a wislisted city from the mongo database,
     * if city doesn't exist for the given username, city, state and country then throw CityInfoNotFoundException
     *
     * @param requestData RemoveFromWatchList
     * @return void
     * @throws CityInfoNotFoundException if city doesn't exist
     */
    @Override
    public void remove(RemoveFromWatchList requestData) throws CityInfoNotFoundException {

        Optional<CityInfo> optional = repository.findByUserNameAndCityAndStateAndCountry(requestData.getUserName(), requestData.getCity(), requestData.getState(), requestData.getCountry());
        if (!optional.isPresent()) {
            throw new CityInfoNotFoundException("No City Found!");
        }
        CityInfo cityInfo = optional.get();
        repository.delete(cityInfo);

    }

    /**
     * Return listed cities from the mongo database,
     * if list doesn't exist for the given username then throw CityInfoNotFoundException
     *
     * @param username String
     * @return void
     * @throws CityInfoNotFoundException if city list doesn't exist
     */
    @Override
    public List<WishListCityDeatils> listWatchListByUserName(String username) throws CityInfoNotFoundException {
        List<CityInfo> cityInfoList = repository.findByUserName(username);
        if (cityInfoList.isEmpty()) {
            throw new CityInfoNotFoundException("No City Found!");
        }
        List<WishListCityDeatils> desired = util.toListCityDetails(cityInfoList);
        return desired;
    }
}

