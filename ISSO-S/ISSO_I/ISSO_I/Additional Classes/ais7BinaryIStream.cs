using System;
using System.Data;
using System.IO;
using System.Text;

namespace ISSO_I
{

    /// <summary>
    /// Бинарный входной поток
    /// </summary>
    internal class Ais7BinaryIStream
    {
	    readonly BinaryReader _reader;

        public bool Eof => _reader.BaseStream.Length - _reader.BaseStream.Position == 0;

	    public long Position
        {
            get => _reader.BaseStream.Position;
		    set => _reader.BaseStream.Position = value;
	    }

        public long Length => _reader.BaseStream.Length;

	    public Ais7BinaryIStream(Stream fs)
        {
            _reader = new BinaryReader(fs);
        }

        public void Seek(long offcet, SeekOrigin origin) { _reader.BaseStream.Seek(offcet, origin); }

        public Guid ReadGuid() { return new Guid(_reader.ReadBytes(16)); }
        public double ReadDouble() { return _reader.ReadDouble(); }
        private decimal ReadDecimal() { return _reader.ReadDecimal(); }
        public float ReadFloat() { return _reader.ReadSingle(); }
        public long ReadInt64() { return _reader.ReadInt64(); }
        public int ReadInt32() { return _reader.ReadInt32(); }
        public short ReadInt16() { return _reader.ReadInt16(); }
        public byte ReadInt8() { return _reader.ReadByte(); }
        public bool ReadBool() { return _reader.ReadBoolean(); }
        public string ReadString() { return Encoding.UTF8.GetString(ReadBytes()); }
        public byte[] ReadBytes()
        {
            var len = _reader.ReadInt32();
            return len > 0 ? _reader.ReadBytes(len) : new byte[0];
        }

        public DateTime ReadDateTime()
        {
            return new DateTime(
                _reader.ReadInt32(),
                _reader.ReadInt32(),
                _reader.ReadInt32(),
                _reader.ReadInt32(),
                _reader.ReadInt32(),
                _reader.ReadInt32(),
                _reader.ReadInt32());
        }

	    public TimeSpan ReadTimeSpan()
	    {
			return new TimeSpan(_reader.ReadInt64());
	    }

        /// <summary>
        /// Чтение таблицы
        /// </summary>
        /// <returns></returns>
        public DataTable ReadDataTable()
        {
            var table = ReadDataTableSchema(out var rCount);

            for (var i = 0; i < rCount; i++)
            {
                var row = ReadDataRow(table);
                table.Rows.Add(row);
            }
            // это для корректной работы RowState
            table.AcceptChanges();
            return table;
        }

        public DataRow ReadDataRow(DataTable table)
        {
            var row = table.NewRow();
            for (var j = 0; j < table.Columns.Count; j++)
                if (ReadBool())
                    row[table.Columns[j]] = Read(table.Columns[j].DataType);
                else
                    row[table.Columns[j]] = DBNull.Value;
            return row;
        }

        public DataTable ReadDataTableSchema(out int rCount)
        {
            var table = new DataTable
            {
                TableName = ReadString()
            };
            var cCount = ReadInt32();
            rCount = ReadInt32();
            for (var i = 0; i < cCount; i++)
            {
                var cl = table.Columns.Add(ReadString(), Type.GetType(ReadString()) ?? throw new InvalidOperationException());
                if (cl.DataType == typeof(DateTime))
                    cl.DateTimeMode = DataSetDateTime.Unspecified;
            }
            return table;
        }

	    /// <summary>
	    /// обобщенный метод чтения
	    /// </summary>
	    /// <returns></returns>
	    public object Read(Type type)
        {
            if (type == typeof(string)) return ReadString();
            if (type == typeof(DateTime)) return ReadDateTime();
            if (type == typeof(double)) return ReadDouble();
            if (type == typeof(float)) return ReadFloat();
            if (type == typeof(long)) return ReadInt64();
            if (type == typeof(int)) return ReadInt32();
            if (type == typeof(short)) return ReadInt16();
            if (type == typeof(byte)) return ReadInt8();
            if (type == typeof(byte[])) return ReadBytes();
            if (type == typeof(bool)) return ReadBool();
            if (type == typeof(Guid)) return ReadGuid();
            if (type == typeof(decimal)) return ReadDecimal();
            if (type == typeof(DataTable)) return ReadDataTable();
            if (type == typeof(TimeSpan)) return ReadTimeSpan();

            throw new NotImplementedException();
        }
        
        /// <summary>
        /// Закрыть поток
        /// </summary>
        public void Close()
        {
            _reader.Close();
        }

        public void Dispose()
        {
            Close();
        }
    }

    /// <summary>
    /// Бинарный выходной поток
    /// </summary>
    public class Ais7BinaryOStream : IDisposable
    {
	    private readonly BinaryWriter _writer;

        /// <summary>
        /// Длина потока
        /// </summary>
        public long Length => _writer.BaseStream.Length;

	    public long Position
        {
            get => _writer.BaseStream.Position;
		    set => _writer.BaseStream.Position = value;
	    }

        public Ais7BinaryOStream(Stream fs)
        {
            _writer = new BinaryWriter(fs);
        }


        public void Write(byte[] array) { _writer.Write(array.Length); _writer.Write(array); }
        public void Write(DateTime date)
        {
            _writer.Write(date.Year);
            _writer.Write(date.Month);
            _writer.Write(date.Day);

            _writer.Write(date.Hour);
            _writer.Write(date.Minute);
            _writer.Write(date.Second);
            _writer.Write(date.Millisecond);
        }
        /// <summary>
        /// Запись таблицы
        /// </summary>
        /// <param name="dataTable"></param>
        public void Write(DataTable dataTable)
        {
            WriteDataTableSchema(dataTable);
            foreach (DataRow row in dataTable.Rows)
                WriteDataRow(dataTable, row);
        }

        /// <summary>
        /// Запись в файл отдельной строки таблицы
        /// </summary>
        /// <param name="dataTable"></param>
        /// <param name="row"></param>
        public void WriteDataRow(DataTable dataTable, DataRow row)
        {
            foreach (DataColumn col in dataTable.Columns)
                if (row[col] != DBNull.Value)
                {
                    Write(true);
                    Write(row[col]);
                }
                else
                    Write(false);
        }

        /// <summary>
        /// Записать в файл схему таблицы
        /// </summary>
        /// <param name="dataTable"></param>
        public void WriteDataTableSchema(DataTable dataTable)
        {
            Write(dataTable.TableName);
            Write(dataTable.Columns.Count);
            Write(dataTable.Rows.Count);
            foreach (DataColumn col in dataTable.Columns)
            {
                Write(col.ColumnName);
                Write(col.DataType.ToString());
            }
        }

        public void Write(double d) { _writer.Write(d); }
        public void Write(decimal d) { _writer.Write(d); }
        public void Write(float f) { _writer.Write(f); }
        public void Write(long i64) { _writer.Write(i64); }
        public void Write(int i32) { _writer.Write(i32); }
        public void Write(short i16) { _writer.Write(i16); }
        public void Write(ushort ui16) { _writer.Write(ui16); }
        public void Write(uint ui32) { _writer.Write(ui32); }
        public void Write(byte i8) { _writer.Write(i8); }
        public void Write(bool bit) { _writer.Write(bit); }
        public void Write(string s) { Write(Encoding.UTF8.GetBytes(s)); }
        public void Write(Guid guid) { _writer.Write(guid.ToByteArray()); }
        /// <summary>
        /// общий метод записи
        /// </summary>
        /// <param name="val"></param>
        public void Write(object val)
        {
            switch (val)
            {
	            case string s:
		            Write(s); return;
	            case long l:
		            Write(l); return;
	            case int i:
		            Write(i); return;
	            case short s1:
		            Write(s1); return;
	            case ushort _:
		            Write((ushort)val); return;
	            case uint _:
		            Write((uint)val); return;
	            case byte _:
		            Write((byte)val); return;
	            case byte[] _:
		            Write((byte[])val); return;
	            case DateTime _:
		            Write((DateTime)val); return;
	            case double _:
		            Write((double)val); return;
	            case decimal _:
		            Write((decimal)val); return;
	            case float _:
		            Write((float)val); return;
	            case bool _:
		            Write((bool)val); return;
	            case Guid _:
		            Write((Guid)val); return;
	            case DataTable _:
		            Write((DataTable)val); return;
            }


	        throw new NotImplementedException($"Обнаружен необрабатываемый тип {val.GetType()}");
        }

        /// <summary>
        /// Закрыть поток
        /// </summary>
        public void Close()
        {
            _writer.Flush();
            _writer.Close();
        }

        /// <summary>
        /// записать содержимое буфера в файл принудительно
        /// </summary>
        public void Flush()
        {
            _writer.Flush();
        }

        public void Dispose()
        {
            Close();
        }
    }
}
