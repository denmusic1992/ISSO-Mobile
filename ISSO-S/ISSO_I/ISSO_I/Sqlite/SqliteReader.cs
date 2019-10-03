using System;
using System.Collections.Generic;
using System.Data;
using System.Diagnostics;
using System.Text;
using ISSO_I.Additional_Classes;
using Mono.Data.Sqlite;

namespace ISSO_I.Sqlite
{
	/// <summary>
	/// После использования обязательно закрывать reader и connection
	/// </summary>
	public class SqliteReader
	{
		public static SqliteDataReader SelectQueryReader(string sql, out SqliteConnection connection)
		{
			connection = null;
			SqliteCommand command = null;
			try
			{
				connection = new SqliteConnection(ConnectionClass.NewDatabasePath);
				command = connection.CreateCommand();
				command.CommandText = sql;
				command.CommandTimeout = 30;
				command.CommandType = CommandType.Text;
				connection.Open();
				var reader = command.ExecuteReader();
				return reader;
			}
			catch (SqliteException ex)
			{
				Debug.WriteLine($"Ошибка при получении DataReader: {ex.Message}");
				command?.Dispose();
				connection?.Close();
				return null;
			}

		}

		public static T SelectScalar<T>(string sql)
		{
			var connection = new SqliteConnection(ConnectionClass.NewDatabasePath);
			var command = connection.CreateCommand();
			command.CommandText = sql;
			command.CommandTimeout = 30;
			command.CommandType = CommandType.Text;
			connection.Open();

			var result = command.ExecuteScalar();
			command.Dispose();
			connection.Close();
			return (T)Convert.ChangeType(result, typeof(T));
		}

		public static int ExecSqliteModifyTransaction(string sql)
		{

			SqliteConnection connection = null;
			SqliteTransaction transaction = null;
			SqliteCommand command = null;
			try
			{
				// Открываем соединение и начинаем транзацкию
				connection = new SqliteConnection(ConnectionClass.NewDatabasePath);
				connection.Open();
				transaction = connection.BeginTransaction();
				command = connection.CreateCommand();
				command.CommandText = sql;
				command.CommandTimeout = 30;
				command.CommandType = CommandType.Text;
				var count = command.ExecuteNonQuery();
				return count;
			}
			catch (SqliteException ex)
			{
				Debug.WriteLine($"Ошибка в запросе БД: {ex.Message}");
				if (transaction != null)
					try
					{
						transaction.Rollback();
					}
					catch (SqliteException ex2)
					{
						Debug.WriteLine($"Откат транзакции вылетел с ошибкой: {ex2.Message}");
					}
					finally
					{
						transaction.Dispose();
					}
				return -1;
			}
			finally
			{
				command?.Dispose();
				transaction?.Dispose();
				if (connection != null)
					try
					{
						connection.Close();
					}
					catch (SqliteException ex)
					{
						Debug.WriteLine($"Закрытие соедиенения вылетело с ошибкой: {ex.Message}");
					}
					finally
					{
						connection.Dispose();
					}
			}
		}
	}
}
