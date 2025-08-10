package br.msmed.voll.qrcodegenetation.ports;

public interface StoragePort {
    String updateFile( byte[] fileDate,String fileName, String contentType);
}
