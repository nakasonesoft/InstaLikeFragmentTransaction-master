package com.f22labs.instalikefragmenttransaction.adapters;

public class GetDataAdapter
{
    //Geral-----------------------------------------------------------------------------------------
    private String id_cliente;

    //pagseguro-------------------------------------------------------------------------------------
    private String status_pagseguro;




    //Carnê--------------------------------------------------------------------------------------
    private String id_carne;
    private String descricao_carne;
    private String valor_carne;
    private String datafinal_carne;
    private String qntd_carne;

    //Despesas--------------------------------------------------------------------------------------
    private String id_despesas;
    private String descricao_despesas;
    private String conta_despesas;
    private String valor_despesas;
    private String comofoipago_despesas;
    private String data_despesas;

    //Receita---------------------------------------------------------------------------------------
    private String id_receita;
    private String descricao_receita;
    private String id_conta;
    private String valor_receita;
    private String praondefoi_receita;
    private String data_receita;

    //Transferencia---------------------------------------------------------------------------------
    private String id_transferencia;
    private String descricao_transferencia;
    private String valor_transferencia;
    private String praondefoi_transferencia;
    private String contaondeveio_transferencia;
    private String data_transferencia;

    //Saque-----------------------------------------------------------------------------------------
    private String id_saque;
    private String descricao_saque;
    private String valor_saque;
    private String praondefoi_saque;
    private String contaondeveio_saque;
    private String data_saque;

    //Depósito--------------------------------------------------------------------------------------
    private String id_deposito;
    private String descricao_deposito;
    private String valor_deposito;
    private String praondefoi_deposito;
    private String contaondeveio_deposito;
    private String data_deposito;

    //Imóvel----------------------------------------------------------------------------------------
    private String id_prestImovel;
    private String descricao_prestImovel;
    private String valor_prestImovel;
    private String conta_prestImovel;
    private String comofoipago_prestImovel;
    private String data_prestImovel;

    //Consórcio-------------------------------------------------------------------------------------
    private String id_consorcio;
    private String descricao_consorcio;
    private String valor_consorcio;
    private String conta_consorcio;
    private String comofoipago_consorcio;
    private String data_consorcio;

    //Outros----------------------------------------------------------------------------------------
    private String id_outros;
    private String descricao_outros;
    private String id_grupo;
    private String id_grupo2;
    private String valor_outros;
    private String data_outros;

    //Contas----------------------------------------------------------------------------------------
    private String id_conta_conta;
    private String id_grupo_conta;
    private String nome_conta;
    private String saldoinicial_conta;
    private String datafechamento_conta;
    private String status;



    //region Despesa
    public String getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(String id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getId_despesas() {
        return id_despesas;
    }

    public void setId_despesas(String id_despesas) {
        this.id_despesas = id_despesas;
    }

    public String getDescricao_despesas() {
        return descricao_despesas;
    }

    public void setDescricao_despesas(String descricao_despesas) {
        this.descricao_despesas = descricao_despesas;
    }

    public String getConta_despesas() {
        return conta_despesas;
    }

    public void setConta_despesas(String conta_despesas) {
        this.conta_despesas = conta_despesas;
    }

    public String getValor_despesas() {
        return valor_despesas;
    }

    public void setValor_despesas(String valor_despesas) {
        this.valor_despesas = valor_despesas;
    }

    public String getComofoipago_despesas() {
        return comofoipago_despesas;
    }

    public void setComofoipago_despesas(String comofoipago_despesas) {
        this.comofoipago_despesas = comofoipago_despesas;
    }

    public String getData_despesas() {
        return data_despesas;
    }

    public void setData_despesas(String data_despesas) {
        this.data_despesas = data_despesas;
    }
//endregion

    //region Receita
    public String getId_receita() {
        return id_receita;
    }

    public void setId_receita(String id_receita) {
        this.id_receita = id_receita;
    }

    public String getDescricao_receita() {
        return descricao_receita;
    }

    public void setDescricao_receita(String descricao_receita) {
        this.descricao_receita = descricao_receita;
    }

    public String getId_conta() {
        return id_conta;
    }

    public void setId_conta(String id_conta) {
        this.id_conta = id_conta;
    }

    public String getValor_receita() {
        return valor_receita;
    }

    public void setValor_receita(String valor_receita) {
        this.valor_receita = valor_receita;
    }

    public String getPraondefoi_receita() {
        return praondefoi_receita;
    }

    public void setPraondefoi_receita(String praondefoi_receita) {
        this.praondefoi_receita = praondefoi_receita;
    }

    public String getData_receita() {
        return data_receita;
    }

    public void setData_receita(String data_receita) {
        this.data_receita = data_receita;
    }
//endregion

    //region Transferencia
    public String getId_transferencia() {
        return id_transferencia;
    }

    public void setId_transferencia(String id_transferencia) {
        this.id_transferencia = id_transferencia;
    }

    public String getDescricao_transferencia() {
        return descricao_transferencia;
    }

    public void setDescricao_transferencia(String descricao_transferencia) {
        this.descricao_transferencia = descricao_transferencia;
    }

    public String getValor_transferencia() {
        return valor_transferencia;
    }

    public void setValor_transferencia(String valor_transferencia) {
        this.valor_transferencia = valor_transferencia;
    }

    public String getPraondefoi_transferencia() {
        return praondefoi_transferencia;
    }

    public void setPraondefoi_transferencia(String praondefoi_transferencia) {
        this.praondefoi_transferencia = praondefoi_transferencia;
    }

    public String getContaondeveio_transferencia() {
        return contaondeveio_transferencia;
    }

    public void setContaondeveio_transferencia(String contaondeveio_transferencia) {
        this.contaondeveio_transferencia = contaondeveio_transferencia;
    }

    public String getData_transferencia() {
        return data_transferencia;
    }

    public void setData_transferencia(String data_transferencia) {
        this.data_transferencia = data_transferencia;
    }
//endregion

    //region Saque
    public String getId_saque() {
        return id_saque;
    }

    public void setId_saque(String id_saque) {
        this.id_saque = id_saque;
    }

    public String getDescricao_saque() {
        return descricao_saque;
    }

    public void setDescricao_saque(String descricao_saque) {
        this.descricao_saque = descricao_saque;
    }

    public String getValor_saque() {
        return valor_saque;
    }

    public void setValor_saque(String valor_saque) {
        this.valor_saque = valor_saque;
    }

    public String getPraondefoi_saque() {
        return praondefoi_saque;
    }

    public void setPraondefoi_saque(String praondefoi_saque) {
        this.praondefoi_saque = praondefoi_saque;
    }

    public String getContaondeveio_saque() {
        return contaondeveio_saque;
    }

    public void setContaondeveio_saque(String contaondeveio_saque) {
        this.contaondeveio_saque = contaondeveio_saque;
    }

    public String getData_saque() {
        return data_saque;
    }

    public void setData_saque(String data_saque) {
        this.data_saque = data_saque;
    }
//endregion

    //region Deposito
    public String getId_deposito() {
        return id_deposito;
    }

    public void setId_deposito(String id_deposito) {
        this.id_deposito = id_deposito;
    }

    public String getDescricao_deposito() {
        return descricao_deposito;
    }

    public void setDescricao_deposito(String descricao_deposito) {
        this.descricao_deposito = descricao_deposito;
    }

    public String getValor_deposito() {
        return valor_deposito;
    }

    public void setValor_deposito(String valor_deposito) {
        this.valor_deposito = valor_deposito;
    }

    public String getPraondefoi_deposito() {
        return praondefoi_deposito;
    }

    public void setPraondefoi_deposito(String praondefoi_deposito) {
        this.praondefoi_deposito = praondefoi_deposito;
    }

    public String getContaondeveio_deposito() {
        return contaondeveio_deposito;
    }

    public void setContaondeveio_deposito(String contaondeveio_deposito) {
        this.contaondeveio_deposito = contaondeveio_deposito;
    }

    public String getData_deposito() {
        return data_deposito;
    }

    public void setData_deposito(String data_deposito) {
        this.data_deposito = data_deposito;
    }
//endregion

    //region Imovel
    public String getId_prestImovel() {
        return id_prestImovel;
    }

    public void setId_prestImovel(String id_prestImovel) {
        this.id_prestImovel = id_prestImovel;
    }

    public String getDescricao_prestImovel() {
        return descricao_prestImovel;
    }

    public void setDescricao_prestImovel(String descricao_prestImovel) {
        this.descricao_prestImovel = descricao_prestImovel;
    }

    public String getValor_prestImovel() {
        return valor_prestImovel;
    }

    public void setValor_prestImovel(String valor_prestImovel) {
        this.valor_prestImovel = valor_prestImovel;
    }

    public String getConta_prestImovel() {
        return conta_prestImovel;
    }

    public void setConta_prestImovel(String conta_prestImovel) {
        this.conta_prestImovel = conta_prestImovel;
    }

    public String getComofoipago_prestImovel() {
        return comofoipago_prestImovel;
    }

    public void setComofoipago_prestImovel(String comofoipago_prestImovel) {
        this.comofoipago_prestImovel = comofoipago_prestImovel;
    }

    public String getData_prestImovel() {
        return data_prestImovel;
    }

    public void setData_prestImovel(String data_prestImovel) {
        this.data_prestImovel = data_prestImovel;
    }
    //endregion

    //region Consorcio
    public String getId_consorcio() {
        return id_consorcio;
    }

    public void setId_consorcio(String id_consorcio) {
        this.id_consorcio = id_consorcio;
    }

    public String getDescricao_consorcio() {
        return descricao_consorcio;
    }

    public void setDescricao_consorcio(String descricao_consorcio) {
        this.descricao_consorcio = descricao_consorcio;
    }

    public String getValor_consorcio() {
        return valor_consorcio;
    }

    public void setValor_consorcio(String valor_consorcio) {
        this.valor_consorcio = valor_consorcio;
    }

    public String getConta_consorcio() {
        return conta_consorcio;
    }

    public void setConta_consorcio(String conta_consorcio) {
        this.conta_consorcio = conta_consorcio;
    }

    public String getComofoipago_consorcio() {
        return comofoipago_consorcio;
    }

    public void setComofoipago_consorcio(String comofoipago_consorcio) {
        this.comofoipago_consorcio = comofoipago_consorcio;
    }

    public String getData_consorcio() {
        return data_consorcio;
    }

    public void setData_consorcio(String data_consorcio) {
        this.data_consorcio = data_consorcio;
    }
    //endregion

    //region Outros
    public String getId_outros() {
        return id_outros;
    }

    public void setId_outros(String id_outros) {
        this.id_outros = id_outros;
    }

    public String getDescricao_outros() {
        return descricao_outros;
    }

    public void setDescricao_outros(String descricao_outros) {
        this.descricao_outros = descricao_outros;
    }

    public String getId_grupo() {
        return id_grupo;
    }

    public void setId_grupo(String id_grupo) {
        this.id_grupo = id_grupo;
    }

    public String getId_grupo2() {
        return id_grupo2;
    }

    public void setId_grupo2(String id_grupo2) {
        this.id_grupo2 = id_grupo2;
    }

    public String getValor_outros() {
        return valor_outros;
    }

    public void setValor_outros(String valor_outros) {
        this.valor_outros = valor_outros;
    }

    public String getData_outros() {
        return data_outros;
    }

    public void setData_outros(String data_outros) {
        this.data_outros = data_outros;
    }
    //endregion

    //region Conta
    public String getId_conta_conta() {
        return id_conta_conta;
    }

    public void setId_conta_conta(String id_conta_conta) {
        this.id_conta_conta = id_conta_conta;
    }

    public String getId_grupo_conta() {
        return id_grupo_conta;
    }

    public void setId_grupo_conta(String id_grupo_conta) {
        this.id_grupo_conta = id_grupo_conta;
    }

    public String getNome_conta() {
        return nome_conta;
    }

    public void setNome_conta(String nome_conta) {
        this.nome_conta = nome_conta;
    }

    public String getSaldoinicial_conta() {
        return saldoinicial_conta;
    }

    public void setSaldoinicial_conta(String saldoinicial_conta) {
        this.saldoinicial_conta = saldoinicial_conta;
    }

    public String getDatafechamento_conta() {
        return datafechamento_conta;
    }

    public void setDatafechamento_conta(String datafechamento_conta) {
        this.datafechamento_conta = datafechamento_conta;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getId_carne() {
        return id_carne;
    }

    public void setId_carne(String id_carne) {
        this.id_carne = id_carne;
    }

    public String getDescricao_carne() {
        return descricao_carne;
    }

    public void setDescricao_carne(String descricao_carne) {
        this.descricao_carne = descricao_carne;
    }

    public String getValor_carne() {
        return valor_carne;
    }

    public void setValor_carne(String valor_carne) {
        this.valor_carne = valor_carne;
    }

    public String getDatafinal_carne() {
        return datafinal_carne;
    }

    public void setDatafinal_carne(String datafinal_carne) {
        this.datafinal_carne = datafinal_carne;
    }

    public String getQntd_carne() {
        return qntd_carne;
    }

    public void setQntd_carne(String qntd_carne) {
        this.qntd_carne = qntd_carne;
    }

    public String getStatus_pagseguro() {
        return status_pagseguro;
    }

    public void setStatus_pagseguro(String status_pagseguro) {
        this.status_pagseguro = status_pagseguro;
    }
    //endregion

}