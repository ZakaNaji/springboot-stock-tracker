package com.znaji.stocktracker.client.fmp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class FmpQuoteOverviewResponse {
    /**
     * FMP json:
     * {
     * 		"symbol": "AAPL",
     * 		"cik": "0000320193",
     * 		"registrantName": "Apple Inc.",
     * 		"sicCode": "3571",
     * 		"sicDescription": "Electronic Computers",
     * 		"sicGroup": "Consumer Electronics",
     * 		"isin": "US0378331005",
     * 		"businessAddress": "ONE APPLE PARK WAY,CUPERTINO CA 95014,(408) 996-1010",
     * 		"mailingAddress": "ONE APPLE PARK WAY,CUPERTINO CA 95014",
     * 		"phoneNumber": "(408) 996-1010",
     * 		"postalCode": "95014",
     * 		"city": "Cupertino",
     * 		"state": "CA",
     * 		"country": "US",
     * 		"description": "Apple Inc. designs, manufactures, and markets smartphones, personal computers, tablets, wearables, and accessories worldwide. The company offers iPhone, a line of smartphones; Mac, a line of personal computers; iPad, a line of multi-purpose tablets; and wearables, home, and accessories comprising AirPods, Apple TV, Apple Watch, Beats products, and HomePod. It also provides AppleCare support and cloud services; and operates various platforms, including the App Store that allow customers to discov...",
     * 		"ceo": "Mr. Timothy D. Cook",
     * 		"website": "https://www.apple.com",
     * 		"exchange": "NASDAQ",
     * 		"stateLocation": "CA",
     * 		"stateOfIncorporation": "CA",
     * 		"fiscalYearEnd": "09-28",
     * 		"ipoDate": "1980-12-12",
     * 		"employees": "164000",
     * 		"secFilingsUrl": "https://www.sec.gov/cgi-bin/browse-edgar?CIK=0000320193",
     * 		"taxIdentificationNumber": "94-2404110",
     * 		"fiftyTwoWeekRange": "164.08 - 260.1",
     * 		"isActive": true,
     * 		"assetType": "stock",
     * 		"openFigiComposite": "BBG000B9XRY4",
     * 		"priceCurrency": "USD",
     * 		"marketSector": "Technology",
     * 		"securityType": null,
     * 		"isEtf": false,
     * 		"isAdr": false,
     * 		"isFund": false
     * 	    }
     */

    private String symbol;
    private String cik;
    private String registrantName;
    private String sicCode;
    private String sicDescription;
    private String sicGroup;
    private String isin;
    private String businessAddress;
    private String mailingAddress;
    private String phoneNumber;
    private String postalCode;
    private String city;
    private String state;
    private String country;
    private String description;
    private String ceo;
    private String website;
    private String exchange;
    private String stateLocation;
    private String stateOfIncorporation;
    private String fiscalYearEnd;
    private String ipoDate;
    private String employees;
    private String secFilingsUrl;
    private String taxIdentificationNumber;
    private String fiftyTwoWeekRange;
    private Boolean isActive;
    private String assetType;
    private String openFigiComposite;
    private String priceCurrency;
    private String marketSector;
    private String securityType;
    private Boolean isEtf;
    private Boolean isAdr;
    private Boolean isFund;
}
