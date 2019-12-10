package io.stacs.nav.drs.api.model.block;import lombok.Getter;import lombok.Setter;import lombok.ToString;import java.math.BigDecimal;import java.util.Date;/** * The type Block po. */@ToString @Getter @Setter public class BlockVO {    /**     * block height     */    private Long height;    /**     * version     */    private String version;    /**     * previous block hash     */    private String previousHash;    /**     * block hash     */    private String blockHash;    /**     * transcation merkel tree root hash     */    private String txRootHash;    /**     * account state merkel tree root hash     */    private String accountRootHash;    /**     * contract state merkel tree root hash     */    private String contractRootHash;    /**     * policy merkel tree root hash     */    private String policyRootHash;    /**     * rs merkel tree root hash     */    private String rsRootHash;    /**     * tx receipt merkel tree root hash     */    private String txReceiptRootHash;    /**     * ca merkle tree root hash     */    private String caRootHash;    /**     * state root hash     */    private String stateRootHash;    /**     * block time     */    private Date blockTime;    /**     * transaction number     */    private Integer txNum;    /**     * the number of transactions recorded by the current block     */    private Long totalTxNum;    /**     * total block size,unit:kb     */    private BigDecimal totalBlockSize;}