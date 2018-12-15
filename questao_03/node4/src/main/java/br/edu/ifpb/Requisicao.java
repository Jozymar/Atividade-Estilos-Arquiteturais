package br.edu.ifpb;

import java.io.Serializable;

public class Requisicao implements Serializable {

    private String operacao;
    private Integer x;
    private Integer y;

    public Requisicao(String operacao, Integer x, Integer y) {
        this.operacao = operacao;
        this.x = x;
        this.y = y;
    }

    public String getOperacao() {
        return operacao;
    }

    public void setOperacao(String operacao) {
        this.operacao = operacao;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Requisicao{" +
                "operacao='" + operacao + '\'' +
                ", x=" + x +
                ", y=" + y +
                '}';
    }
}
