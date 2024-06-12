package Spring.service.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_keys")
public class Keys {
    //    "BYBIT_API_KEY"
//    "BYBIT_API_SECRET"
//    "BINANCE_API_KEY"
//    "BINANCE_SECRET_KEY"
//    "GATEIO_API_KEY"
//    "GATEIO_API_SECRET"
//    "KUCOIN_API_KEY"
//    "KUCOIN_API_SECRET"
//    "KUCOIN_API_PASSPHRASE"
    @Id
    @GeneratedValue
    private Integer id;

    private String bybitApiKey;
    private String bybitApiSecret;
    private String binanceApiKey;
    private String binanceApiSecret;
    private String gateioApiKey;
    private String gateioApiSecret;
    private String kucoinApiKey;
    private String kucoinApiSecret;
    private String kucoinApiPassphrase;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

}
