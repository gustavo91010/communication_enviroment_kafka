import json
from avro.schema import Parse

from confluent_kafka import  Consumer, KafkaError

from utils.avro_services import deserialize_avro



def consume_messages(topic,schema_json, kafka_config):
    schema = Parse(json.dumps(schema_json)) #convertendo o json em avro
    consumer = Consumer(kafka_config)
    consumer.subscribe([topic])
    try:
        while True:
            msg = consumer.poll(1.0) 
            if msg is None:
                continue
            if msg.error():
                if msg.error().code() != KafkaError._PARTITION_EOF:
                    print(f"Erro no kafka: {msg.error()}")
                continue
            
            avro_data = deserialize_avro(msg.value(), schema)
            print(f"Mensagem recebida (Avro): {avro_data}")
    except KeyboardInterrupt:
        print("Encerrando consumidor")
    finally:
        consumer.close()