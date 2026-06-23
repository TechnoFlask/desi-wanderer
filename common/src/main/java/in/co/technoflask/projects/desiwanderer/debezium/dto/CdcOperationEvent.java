package in.co.technoflask.projects.desiwanderer.debezium.dto;

public interface CdcOperationEvent {

  CdcOperation operation();

  Long lsn();
}
