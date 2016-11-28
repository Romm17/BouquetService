package com.app.entity;

/**
 *
 * This class represents status of bouquet order
 * Created by romm on 21.11.16.
 */
public enum BouquetOrderStatus {

    /**
     * This status is assigned when customer choose bouquets but not confirm order
     */
    IN_PROGRESS,

    /**
     * This status is assigned when order confirmed
     */
    BOUGHT

}
