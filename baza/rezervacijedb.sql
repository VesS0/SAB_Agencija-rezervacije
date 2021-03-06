USE [master]
GO
/****** Object:  Database [Rezervacije]    Script Date: 7/5/2016 11:01:39 PM ******/
CREATE DATABASE [Rezervacije]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'Rezervacije', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.VLADA\MSSQL\DATA\Rezervacije.mdf' , SIZE = 5120KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'Rezervacije_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.VLADA\MSSQL\DATA\Rezervacije_log.ldf' , SIZE = 2048KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [Rezervacije] SET COMPATIBILITY_LEVEL = 120
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [Rezervacije].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [Rezervacije] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [Rezervacije] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [Rezervacije] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [Rezervacije] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [Rezervacije] SET ARITHABORT OFF 
GO
ALTER DATABASE [Rezervacije] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [Rezervacije] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [Rezervacije] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [Rezervacije] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [Rezervacije] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [Rezervacije] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [Rezervacije] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [Rezervacije] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [Rezervacije] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [Rezervacije] SET  DISABLE_BROKER 
GO
ALTER DATABASE [Rezervacije] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [Rezervacije] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [Rezervacije] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [Rezervacije] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [Rezervacije] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [Rezervacije] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [Rezervacije] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [Rezervacije] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [Rezervacije] SET  MULTI_USER 
GO
ALTER DATABASE [Rezervacije] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [Rezervacije] SET DB_CHAINING OFF 
GO
ALTER DATABASE [Rezervacije] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [Rezervacije] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
ALTER DATABASE [Rezervacije] SET DELAYED_DURABILITY = DISABLED 
GO
USE [Rezervacije]
GO
/****** Object:  Table [dbo].[Adresa]    Script Date: 7/5/2016 11:01:39 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Adresa](
	[idAdr] [int] IDENTITY(1,1) NOT NULL,
	[drzava] [varchar](50) NOT NULL,
	[grad] [varchar](50) NOT NULL,
	[ulica] [varchar](50) NOT NULL,
	[broj] [int] NOT NULL,
 CONSTRAINT [PK_Adresa] PRIMARY KEY CLUSTERED 
(
	[idAdr] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Apartman]    Script Date: 7/5/2016 11:01:39 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Apartman](
	[idApar] [int] IDENTITY(1,1) NOT NULL,
	[naziv] [varchar](50) NOT NULL,
	[opis] [varchar](1024) NOT NULL,
	[zakljucan] [bit] NOT NULL CONSTRAINT [DF_Apartman_zakljucan]  DEFAULT ((0)),
	[idKor] [int] NOT NULL,
	[idAdr] [int] NOT NULL,
	[brSoba] [int] NOT NULL CONSTRAINT [DF_Apartman_brSoba]  DEFAULT ((0)),
	[brRez] [int] NOT NULL CONSTRAINT [DF_Apartman_brRez]  DEFAULT ((0)),
 CONSTRAINT [PK_Apartman] PRIMARY KEY CLUSTERED 
(
	[idApar] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Korisnik]    Script Date: 7/5/2016 11:01:39 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Korisnik](
	[idKor] [int] IDENTITY(1,1) NOT NULL,
	[korime] [varchar](50) NULL,
	[sifra] [varchar](50) NULL,
	[email] [varchar](50) NULL,
	[brTel] [varchar](50) NULL,
	[ime] [varchar](50) NULL,
	[prezime] [varchar](50) NULL,
 CONSTRAINT [PK_Korisnik] PRIMARY KEY CLUSTERED 
(
	[idKor] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Kupac]    Script Date: 7/5/2016 11:01:39 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Kupac](
	[idKor] [int] NOT NULL,
	[brKart] [varchar](50) NOT NULL,
	[brRez] [int] NOT NULL CONSTRAINT [DF_Kupac_brRez]  DEFAULT ((0)),
 CONSTRAINT [PK_Kupac] PRIMARY KEY CLUSTERED 
(
	[idKor] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Prodavac]    Script Date: 7/5/2016 11:01:39 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Prodavac](
	[idKor] [int] NOT NULL,
	[POS] [varchar](50) NOT NULL,
	[brApart] [int] NOT NULL CONSTRAINT [DF_Prodavac_brApart]  DEFAULT ((0)),
	[brRez] [int] NOT NULL CONSTRAINT [DF_Prodavac_brRez]  DEFAULT ((0)),
 CONSTRAINT [PK_Prodavac] PRIMARY KEY CLUSTERED 
(
	[idKor] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Rezervacija]    Script Date: 7/5/2016 11:01:39 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Rezervacija](
	[idRez] [int] IDENTITY(1,1) NOT NULL,
	[datumOd] [date] NOT NULL,
	[datumDo] [date] NOT NULL,
	[idSob] [int] NOT NULL,
	[idKor] [int] NOT NULL,
 CONSTRAINT [PK_Rezervacija] PRIMARY KEY CLUSTERED 
(
	[idRez] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Soba]    Script Date: 7/5/2016 11:01:39 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Soba](
	[idSob] [int] IDENTITY(1,1) NOT NULL,
	[brOsoba] [int] NOT NULL,
	[rb] [int] NOT NULL,
	[opis] [varchar](1024) NOT NULL,
	[zakljucano] [bit] NOT NULL CONSTRAINT [DF_Soba_zakljucano]  DEFAULT ((0)),
	[idApar] [int] NOT NULL,
	[brRez] [int] NOT NULL CONSTRAINT [DF_Soba_brRez]  DEFAULT ((0)),
 CONSTRAINT [PK_Soba] PRIMARY KEY CLUSTERED 
(
	[idSob] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  UserDefinedFunction [dbo].[fProdavacDohvatiRezervacije]    Script Date: 7/5/2016 11:01:39 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================

CREATE FUNCTION [dbo].[fProdavacDohvatiRezervacije]
(	
	-- Add the parameters for the function here
	@apartmentId int
)
RETURNS TABLE 
AS
RETURN 
(
	select S.idKor, S.brRez
	from Prodavac S, Apartman A
	where S.idKor = A.idKor and A.idApar = @apartmentId
)


GO
ALTER TABLE [dbo].[Apartman]  WITH CHECK ADD  CONSTRAINT [FK_Apartman_Adresa] FOREIGN KEY([idAdr])
REFERENCES [dbo].[Adresa] ([idAdr])
GO
ALTER TABLE [dbo].[Apartman] CHECK CONSTRAINT [FK_Apartman_Adresa]
GO
ALTER TABLE [dbo].[Apartman]  WITH CHECK ADD  CONSTRAINT [FK_Apartman_Korisnik] FOREIGN KEY([idKor])
REFERENCES [dbo].[Korisnik] ([idKor])
GO
ALTER TABLE [dbo].[Apartman] CHECK CONSTRAINT [FK_Apartman_Korisnik]
GO
ALTER TABLE [dbo].[Kupac]  WITH CHECK ADD  CONSTRAINT [FK_Kupac_Korisnik] FOREIGN KEY([idKor])
REFERENCES [dbo].[Korisnik] ([idKor])
GO
ALTER TABLE [dbo].[Kupac] CHECK CONSTRAINT [FK_Kupac_Korisnik]
GO
ALTER TABLE [dbo].[Prodavac]  WITH CHECK ADD  CONSTRAINT [FK_Prodavac_Korisnik] FOREIGN KEY([idKor])
REFERENCES [dbo].[Korisnik] ([idKor])
GO
ALTER TABLE [dbo].[Prodavac] CHECK CONSTRAINT [FK_Prodavac_Korisnik]
GO
ALTER TABLE [dbo].[Rezervacija]  WITH CHECK ADD  CONSTRAINT [FK_Rezervacija_Kupac] FOREIGN KEY([idKor])
REFERENCES [dbo].[Kupac] ([idKor])
GO
ALTER TABLE [dbo].[Rezervacija] CHECK CONSTRAINT [FK_Rezervacija_Kupac]
GO
ALTER TABLE [dbo].[Rezervacija]  WITH CHECK ADD  CONSTRAINT [FK_Rezervacija_Soba] FOREIGN KEY([idSob])
REFERENCES [dbo].[Soba] ([idSob])
GO
ALTER TABLE [dbo].[Rezervacija] CHECK CONSTRAINT [FK_Rezervacija_Soba]
GO
ALTER TABLE [dbo].[Soba]  WITH CHECK ADD  CONSTRAINT [FK_Soba_Apartman] FOREIGN KEY([idApar])
REFERENCES [dbo].[Apartman] ([idApar])
GO
ALTER TABLE [dbo].[Soba] CHECK CONSTRAINT [FK_Soba_Apartman]
GO
/****** Object:  StoredProcedure [dbo].[SPApartmanUpdateBrRez]    Script Date: 7/5/2016 11:01:39 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SPApartmanUpdateBrRez]
	@apartmentId int,
	@incr int
AS
BEGIN
	update Apartman
	set brRez = brRez + @incr
	where idApar = @apartmentId
END


GO
/****** Object:  StoredProcedure [dbo].[SPApartmanUpdateBrSoba]    Script Date: 7/5/2016 11:01:39 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[SPApartmanUpdateBrSoba]
	@apartmentId int,
	@incr int
AS
BEGIN
	update Apartman 
	set brSoba = brSoba + @incr
	where idApar = @apartmentId;
END


GO
/****** Object:  StoredProcedure [dbo].[SPKupacUpdateBrRez]    Script Date: 7/5/2016 11:01:39 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================

CREATE PROCEDURE [dbo].[SPKupacUpdateBrRez] 
	@customerId int,
	@incr int
AS
BEGIN
	update Kupac
	set brRez = brRez + @incr
	where idKor = @customerId
END


GO
/****** Object:  StoredProcedure [dbo].[SPProdavacUpdateBrApart]    Script Date: 7/5/2016 11:01:39 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[SPProdavacUpdateBrApart] 
	@userId int,
	@incr int
AS
BEGIN
	update Prodavac
	set brApart= brApart + @incr
	where idKor = @userId;

END


GO
/****** Object:  StoredProcedure [dbo].[SPSobaUpdateBrRez]    Script Date: 7/5/2016 11:01:39 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SPSobaUpdateBrRez]
	@apartmentId int,
	@roomNo int,
	@incr int
AS
BEGIN
	update Soba
	set brRez = brRez + @incr
	where idApar = @apartmentId and rb = @roomNo

END

GO
USE [master]
GO
ALTER DATABASE [Rezervacije] SET  READ_WRITE 
GO
