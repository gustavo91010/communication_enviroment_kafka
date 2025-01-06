import io
import json
from io import BytesIO
from avro.schema import Parse
from avro.io import DatumWriter, DatumReader, BinaryEncoder, BinaryDecoder


def serialize_avro(data, json_schema):
    schema_json = json.dumps(json_schema)
    schema = Parse(schema_json)
    writer = DatumWriter(schema)
    bytes_io = io.BytesIO()
    encoder = BinaryEncoder(bytes_io)
    writer.write(data, encoder)
    return bytes_io.getvalue()


def deserialize_avro(avro_data, schema):
    try:
        bytes_io = io.BytesIO(avro_data)
        
        # Exibindo os primeiros bytes antes do seek para analisar os dados
        print(f"Dados antes do seek: {bytes_io.getvalue()[:50]}...")  # Exibe os primeiros 50 bytes
        
        bytes_io.seek(5)  # Pulando os 5 primeiros bytes
        
        # Exibindo os dados após o seek(5)
        print(f"Dados após seek(5): {bytes_io.getvalue()[:50]}...")

        # Verificando o tamanho restante dos dados após seek
        remaining_data = bytes_io.read()
        print(f"Dados restantes após o seek(5): {remaining_data[:50]}...")

        decoder = BinaryDecoder(bytes_io)
        reader = DatumReader(schema)
        return reader.read(decoder)
    except Exception as e:
        print(f"Erro ao processar Avro: {e}")
        raise
# def deserialize_avro(avro_data, schema):
#     bytes_io = io.BytesIO(avro_data)
#  # Logando os dados antes do seek
#     print(f"Dados antes do seek: {bytes_io.getvalue()[:50]}...")  # Exibindo apenas os primeiros 50 bytes para não ficar muito grande

#     # bytes_io.seek(5)  # Pulando os 5 primeiros bytes
        
#         # Logando os dados após o seek
#     print(f"Dados após seek(5): {bytes_io.getvalue()[:50]}...")
#     decoder = BinaryDecoder(bytes_io)
#     reader = DatumReader(schema)
#     return reader.read(decoder)

