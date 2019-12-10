package io.stacs.nav.drs.api.model;import lombok.Getter;import lombok.Setter;/** * tx po * * @author lingchao * @create 2018年03月27日19 :19 */@Getter @Setter public class TransactionPO {    private static final long serialVersionUID = 1L;    /**     * transaction id     */    private String txId;    /**     * the save data of the core     */    private String bizModel;    /**     * policy id     */    private String policyId;    /**     * the lock time of the tx . use in rs and slave to deal tx     */    private Long lockTime;    /**     * the transaction create time     */    private Long sendTime;    /**     * the version of the tx     */    private String version;    /**     * the block height of the tx     */    private Long blockHeight;    /**     * the create time of the block for the tx     */    private Long blockTime;    /**     * the tx sender's rsId     */    private String sender;    /**     * the signed datas by json     */    private String signDatas;    /**     * action list     */    private String actionDatas;    /**     * the tx execute result 0:fail 1:success     */    private String executeResult;    /**     * execute error code     */    private String errorCode;    /**     * execute error message     */    private String errorMessage;    /**     * the type of transaction     */    private String txType;    /**     * policy version of tx     */    private int policyVersion;    /**     * The number of deals     */    private int dealCount = 1;    /**     * maximum fee allowed     */    private String maxAllowFee;    /**     * actual fee amount     */    private String feeAmount;    /**     * address of fee payment     */    private String feePaymentAddress;    /**     * the data of receipt     */    private String receiptData;    /**     * fee currency     */    private String feeCurrency;    private String bdCode;    private String submitter;    private String submitterSign;}