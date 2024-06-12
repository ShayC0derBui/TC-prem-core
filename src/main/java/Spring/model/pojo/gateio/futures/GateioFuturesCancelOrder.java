package Spring.model.pojo.gateio.futures;

import lombok.Data;

@Data
public class GateioFuturesCancelOrder {
    private int id;
    private int user;
    private String contract;
    private long create_time;
    private int size;
    private int iceberg;
    private int left;
    private String price;
    private String fill_price;
    private String mkfr;
    private String tkfr;
    private String tif;
    private int refu;
    private boolean is_reduce_only;
    private boolean is_close;
    private boolean is_liq;
    private String text;
    private String status;
    private long finish_time;
    private String finish_as;
    private int stp_id;
    private String stp_act;
    private String amend_text;
}
