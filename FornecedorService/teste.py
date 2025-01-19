import io
import fastavro
import logging

# Configuração do logging
logging.basicConfig(level=logging.INFO)
logger = logging.getLogger(__name__)

schema = {
    "type": "record",
    "name": "Order",
    "fields": [
        {"name": "code", "type": "string"},
        {
            "name": "items",
            "type": {
                "type": "array",
                "items": {
                    "type": "record",
                    "name": "OrderItem",
                    "fields": [
                        {"name": "name", "type": "string"},
                        {"name": "brand", "type": "string"},
                        {"name": "quantity", "type": "double"}
                    ]
                }
            }
        },
        {"name": "timestamp", "type": "string"}
    ]
}

def deserialize_avro(avro_data, schema):
    try:
        logger.info(f"Brutos recebidos (bytes): {avro_data}")

        bytes_io = io.BytesIO(avro_data)

        # Exibindo cabeçalho antes do seek
        header = avro_data[:5]
        logger.info(f"Cabeçalho inicial (5 bytes): {header}")

        # Pulando o cabeçalho
        bytes_io.seek(5)

        # Verificando dados após o seek
        remaining_data = bytes_io.read()
        logger.info(f"Dados restantes após seek(5): {remaining_data[:50]}...")

        # Decodificando mensagem Avro
        bytes_io.seek(5)  # Reposicionando ponteiro antes da leitura
        decoded_message = fastavro.schemaless_reader(bytes_io, schema)
        logger.info(f"Mensagem decodificada: {decoded_message}")

        return decoded_message

    except Exception as e:
        logger.error(f"Erro ao processar Avro: {e}")
        raise

# Exemplo de uso
if __name__ == "__main__":
    # Substitua pelo dado Avro real
    raw_data = b'\x00\x00\x00\x00>Hb9d10cd8-f35f-48bb-aaba-6e55b556d88b\x00:2025-01-18T23:14:44.945056633'

    try:
        mensagem = deserialize_avro(raw_data, schema)
        print("Mensagem processada:", mensagem)
    except Exception as ex:
        print("Erro durante o processamento:", ex)
