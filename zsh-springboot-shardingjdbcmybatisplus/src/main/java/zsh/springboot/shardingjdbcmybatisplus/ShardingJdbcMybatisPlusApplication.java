package zsh.springboot.shardingjdbcmybatisplus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.Transactional;
import zsh.springboot.shardingjdbcmybatisplus.ds.model.TOrder;
import zsh.springboot.shardingjdbcmybatisplus.ds.model.TOrderItem;
import zsh.springboot.shardingjdbcmybatisplus.ds.service.TOrderItemService;
import zsh.springboot.shardingjdbcmybatisplus.ds.service.TOrderService;

import java.util.ArrayList;
import java.util.List;

@MapperScan(value = "zsh.springboot.shardingjdbcmybatisplus.ds.dao*")
@SpringBootApplication
public class ShardingJdbcMybatisPlusApplication {

    @Autowired
    private TOrderService tOrderService;
    @Autowired
    private TOrderItemService tOrderItemService;

    public static void main(String[] args) {
        ShardingJdbcMybatisPlusApplication app = SpringApplication.run(ShardingJdbcMybatisPlusApplication.class, args).getBean(ShardingJdbcMybatisPlusApplication.class);
        app.truncate();
        app.insert();
        app.select();
    }

    @Transactional(rollbackFor = Throwable.class)
    public void insert() {
        List<TOrder> orderList = tOrderService.list();
        System.out.println(orderList);
        List<Long> result = new ArrayList<>(2);
        for (int i = 1; i <= 2; i++) {
            TOrder order = new TOrder();
            order.setUserId(i);
            order.setAddressId(Long.valueOf(i));
            order.setStatus("INSERT_TEST");
            tOrderService.save(order);
            TOrderItem item = new TOrderItem();
            item.setOrderId(order.getOrderId());
            item.setUserId(i);
            item.setStatus("INSERT_TEST");
            tOrderItemService.save(item);
            result.add(order.getOrderId());
        }
        List<TOrder> list = tOrderService.list();
        System.out.println(list);
    }

    public void select() {
        List<TOrder> orderList = tOrderService.list();
        System.out.println(orderList);
        List<TOrderItem> orderItemList = tOrderItemService.list();
        System.out.println(orderItemList);
    }

    public void truncate() {
        tOrderService.truncate();
        tOrderItemService.truncate();
    }
}
