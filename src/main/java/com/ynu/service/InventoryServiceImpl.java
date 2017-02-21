package com.ynu.service;

import com.ynu.dto.Inventory;
import com.ynu.mapper.InventoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by YANG on 2017/2/21.
 */

@Service
@Transactional
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    private InventoryMapper inventoryMapper;

    public void insertInventory(Inventory inventory){
        inventoryMapper.insertInventory(inventory);
    }

}
