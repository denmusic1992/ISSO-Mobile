using System;
using CommonClassesLibrary.Interfaces;
using ISSO_I.IssoViewPages.ForDefectTable.Models;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Storage.ValueConversion;
using Xamarin.Forms;

namespace ISSO_I.Sqlite
{
    public sealed class DefectContext : DbContext
    {
	    //private string _dbPath { get; set; }



		public DbSet<defects> i_defect { get; set; }
		public DbSet<defMods> i_def_mod { get; set; }
		public DbSet<defDescr> i_def_descr { get; set; }
		public DbSet<FotoDefects> i_foto_def { get; set; }


	    public DefectContext()
	    {
		    Database.EnsureCreated();
	    }

	    protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
	    {
		    var dbPath = DependencyService.Get<IPath>().GetDatabasePath(App.DatabaseName);
		    optionsBuilder.UseSqlite($"Filename={dbPath}");
	    }

	    protected override void OnModelCreating(ModelBuilder modelBuilder)
	    {
			// Для i_defect
		    // Определение составного первичного ключа
		    modelBuilder.Entity<defects>().HasKey(p => new { p.c_isso, p.n_def });
			// Определение Nullable полей
		    modelBuilder.Entity<defects>().Property(p => p.n_constr).IsRequired(false);
		    modelBuilder.Entity<defects>().Property(p => p.b).IsRequired(false);
		    modelBuilder.Entity<defects>().Property(p => p.b1).IsRequired(false);
		    modelBuilder.Entity<defects>().Property(p => p.d).IsRequired(false);
		    modelBuilder.Entity<defects>().Property(p => p.d1).IsRequired(false);
		    modelBuilder.Entity<defects>().Property(p => p.r).IsRequired(false);
		    modelBuilder.Entity<defects>().Property(p => p.r1).IsRequired(false);
		    modelBuilder.Entity<defects>().Property(p => p.g).HasDefaultValue(false);
		    modelBuilder.Entity<defects>().Property(p => p.g1).HasDefaultValue(false);
		    modelBuilder.Entity<defects>().Property(p => p.datef).IsRequired(false);
			
			// Для i_def_mod
		    modelBuilder.Entity<defMods>().HasKey(p => new {p.c_isso, p.n_def, p.date});
		    modelBuilder.Entity<defMods>().Property(p => p.l_def).IsRequired(false);
		    modelBuilder.Entity<defMods>().Property(p => p.w_def).IsRequired(false);
		    modelBuilder.Entity<defMods>().Property(p => p.c_rem).IsRequired(false);
		    modelBuilder.Entity<defMods>().Property(p => p.v_rem).IsRequired(false);

			// Для i_def_descr
		    modelBuilder.Entity<defDescr>().HasKey(p => new {p.c_isso, p.n_def, p.date, p.c_defparam});
		    modelBuilder.Entity<defDescr>().Property(p => p.value).IsRequired(false);
		    modelBuilder.Entity<defDescr>().Property(p => p.b1).IsRequired(false);
		    modelBuilder.Entity<defDescr>().Property(p => p.d).IsRequired(false);
		    modelBuilder.Entity<defDescr>().Property(p => p.d1).IsRequired(false);
		    modelBuilder.Entity<defDescr>().Property(p => p.r).IsRequired(false);
		    modelBuilder.Entity<defDescr>().Property(p => p.r1).IsRequired(false);
		    modelBuilder.Entity<defDescr>().Property(p => p.g).HasDefaultValue(false);
		    modelBuilder.Entity<defDescr>().Property(p => p.g1).HasDefaultValue(false);

			// Для i_foto_def
		    modelBuilder.Entity<FotoDefects>().HasKey(p => new {p.c_isso, p.n_def, date = (DateTime) p.date});
		    modelBuilder.Entity<FotoDefects>().Property(p => p.foto).IsRequired(false);

			var dateTimeConverter = new ValueConverter<DateTime, long>(
				// В БД
				v => new DateTimeOffset(v).ToUnixTimeMilliseconds(),
				// Из БД
				v => DateTimeOffset.FromUnixTimeMilliseconds(v).DateTime
			);
		    var byteArrayConverter = new ValueConverter<byte[], string>(
				// В БД
				v => Convert.ToBase64String(v),
				// Из БД
				v => Convert.FromBase64String(v)
			    );

			// Определение конвертации поля в БД
			modelBuilder.Entity<defects>().Property(p => p.date)
				.HasConversion(dateTimeConverter);
		    modelBuilder.Entity<defMods>().Property(p => p.date)
			    .HasConversion(dateTimeConverter);
		    modelBuilder.Entity<defDescr>().Property(p => p.date)
			    .HasConversion(dateTimeConverter);
		    modelBuilder.Entity<FotoDefects>().Property(p => p.date)
			    .HasConversion(dateTimeConverter);
		    modelBuilder.Entity<FotoDefects>().Property(p => p.foto)
			    .HasConversion(byteArrayConverter);
	    }
    }
}