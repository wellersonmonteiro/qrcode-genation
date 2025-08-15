package br.msmed.voll.qrcodegenetation.infraestruture;

import br.msmed.voll.qrcodegenetation.ports.StoragePort;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@Component
public class S3StorageAdpter implements StoragePort {
    private final S3Client s3Client;
    private final String bucketName;
    private final String region;

    public S3StorageAdpter(@Value("${aws.s3.region}") String region,
                           @Value("${aws.s3.bucket.name}") String bucketName) {
        this.bucketName = bucketName;
        this.region = region;
        this.s3Client = S3Client.builder()
                .region(Region.of(region))
                .build();
    }

    @Override
    public String updateFile(byte[] fileDate, String fileName, String contentType) {
        PutObjectRequest putObjectAclRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(fileName)
                .contentType(contentType)
                .build();

        s3Client.putObject(putObjectAclRequest, RequestBody.fromBytes(fileDate));

        return String.format("https://%s.s3.%s.amazonaws.com/%s", bucketName, region, fileName);

        //
    }
}
