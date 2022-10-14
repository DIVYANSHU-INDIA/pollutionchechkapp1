package com.stackroute.wishlistms.util;

import com.stackroute.wishlistms.dto.AddToWatchList;
import com.stackroute.wishlistms.dto.WishListCityDeatils;

import com.stackroute.wishlistms.entity.CityInfo;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * utility class for wishlist module
 *
 * @author Govind
 */
@Component
public class WishlistCityUtil {
    /**
     * convert object of type CityInfo i.e, object of entity class to Object of type WishListCityDeatils,
     *
     * @param cityInfo CityInfo,
     * @return WishListCityDeatils
     */
    public WishListCityDeatils toCityDetails(CityInfo cityInfo) {
        WishListCityDeatils desired = new WishListCityDeatils();
        desired.setUserName(cityInfo.getUserName());
        desired.setCity(cityInfo.getCity());
        desired.setState(cityInfo.getState());
        desired.setCountry(cityInfo.getCountry());
        desired.setLocation(cityInfo.getLocation());
        desired.setWeather(cityInfo.getWeather());
        desired.setPollution(cityInfo.getPollution());

        return desired;
    }

    /**
     * convert object of type AddToWatchList i.e, object of a dto class to Object of type CityInfo,
     *
     * @param cityInfo AddToWatchList,
     * @return CityInfo
     */
    public CityInfo toCityInfo(AddToWatchList cityInfo) {
        CityInfo desired = new CityInfo();
        desired.setUserName(cityInfo.getUserName());
        desired.setUserName(cityInfo.getUserName());
        desired.setCity(cityInfo.getCity());
        desired.setState(cityInfo.getState());
        desired.setCountry(cityInfo.getCountry());
        desired.setLocation(cityInfo.getLocation());
        desired.setWeather(cityInfo.getWeather());
        desired.setPollution(cityInfo.getPollution());

        return desired;
    }

    /**
     * convert Collection<CityInfo> cityInfos  to List<WishListCityDeatils>,
     *
     * @param cityInfos Collection<CityInfo>,
     * @return List<WishListCityDeatils>
     */

    public List<WishListCityDeatils> toListCityDetails(Collection<CityInfo> cityInfos) {
        List<WishListCityDeatils> desired = new ArrayList<>();
        for (CityInfo city : cityInfos) {
            WishListCityDeatils details = toCityDetails(city);
            desired.add(details);
        }
        return desired;

    }
}
