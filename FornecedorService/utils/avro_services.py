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
    bytes_io = io.BytesIO(avro_data)
    bytes_io.seek(5) #o Confluent adiciona 5 bytes extra antes dos dados típicos formatados em avro
    decoder = BinaryDecoder(bytes_io)
    reader = DatumReader(schema)
    return reader.read(decoder)

