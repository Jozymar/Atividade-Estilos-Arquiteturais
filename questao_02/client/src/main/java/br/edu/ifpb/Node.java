package br.edu.ifpb;

import java.net.SocketAddress;

public class Node {

    private SocketAddress endereco;
    private String nome;
    private boolean replica;

    public Node(SocketAddress endereco, String nome, boolean replica) {
        this.endereco = endereco;
        this.nome = nome;
        this.replica = replica;
    }

    public SocketAddress getEndereco() {
        return endereco;
    }

    public void setEndereco(SocketAddress endereco) {
        this.endereco = endereco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean isReplica() {
        return replica;
    }

    public void setReplica(boolean replica) {
        this.replica = replica;
    }

    @Override
    public String toString() {
        return "Node{" +
                "endereco=" + endereco +
                ", nome='" + nome + '\'' +
                ", replica=" + replica +
                '}';
    }
}
