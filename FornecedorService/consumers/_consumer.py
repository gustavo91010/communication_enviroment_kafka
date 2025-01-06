import json
import os
from dotenv import load_dotenv
from avro.schema import Parse
from confluent_kafka import Consumer, KafkaError
from utils.avro_services import deserialize_avro


load_dotenv()
kafka_config = {
    "bootstrap.servers": os.getenv("BOOTSTRAP_SERVERS"),
    "group.id": os.getenv("GROUP_ID"),
    "auto.offset.reset": os.getenv("AUTO_OFFSET_RESET"),
}


def consume_messages(topic, schema_json):
    load_dotenv()
    schema = Parse(json.dumps(schema_json))  # Convertendo o JSON em Avro
    consumer = Consumer(kafka_config)
    consumer.subscribe([os.getenv(topic)])
    try:
        while True:
            msg = consumer.poll(1.0)
            if msg is None:
                continue
            if msg.error():
                if msg.error().code() != KafkaError._PARTITION_EOF:
                    print(f"Erro no kafka: {msg.error()}")
                continue

            # Logando os dados brutos recebidos para inspeção
            print(f"Dados brutos recebidos (bytes): {msg.value()}")

            try:
                avro_data = deserialize_avro(msg.value(), schema)
                print(f"Mensagem recebida do tópico: {topic}")
                print(f"Code: {avro_data['code']}")
                print(json.dumps(avro_data, indent=4))

                return avro_data
            except Exception as e:
                print(f"Erro ao desserializar a mensagem: {e}")
                print(f"Erro ao consumir o tópico: {topic}")
    except KeyboardInterrupt:
        print("Encerrando consumidor")
    finally:
        consumer.close()
# def consume_messages(topic, schema_json):
#     load_dotenv()
#     schema = Parse(json.dumps(schema_json))  # Convertendo o JSON em Avro
#     consumer = Consumer(kafka_config)
#     consumer.subscribe([os.getenv(topic)])
#     try:
#         while True:
#             msg = consumer.poll(1.0)
#             if msg is None:
#                 continue
#             if msg.error():
#                 if msg.error().code() != KafkaError._PARTITION_EOF:
#                     print(f"Erro no kafka: {msg.error()}")
#                 continue
#             print(f"Ve se vai... {msg.value()}")
#             try:
#                 avro_data = deserialize_avro(msg.value(), schema)
#                 print(f"Mensagem recebida do tópico: {topic}")
#                 print(f"Code: {avro_data['code']}")
                
#                 print(json.dumps(avro_data, indent=4))

#                 return avro_data
#             except Exception as e:
#                 print(f"Erro ao desserializar a mensagem: {e}")
#                 print(f"Erro ao consumir o tópico: {topic}")
#     except KeyboardInterrupt:
#         print("Encerrando consumidor")
#     finally:
#         consumer.close()
