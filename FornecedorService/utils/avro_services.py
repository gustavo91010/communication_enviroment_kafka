import io
import json
from avro.schema import Parse
from avro.io import DatumWriter,DatumReader, BinaryEncoder,BinaryDecoder



# Convertendo o esquema para JSON
# schema_json = json.dumps(AVRO_SCHEMA)
# schema = Parse(schema_json)

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
    decoder = BinaryDecoder(bytes_io)
    reader = DatumReader(schema)
    return reader.read(decoder)