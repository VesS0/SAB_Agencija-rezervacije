-- ================================================
-- Template generated from Template Explorer using:
-- Create Procedure (New Menu).SQL
--
-- Use the Specify Values for Template Parameters 
-- command (Ctrl-Shift-M) to fill in the parameter 
-- values below.
--
-- This block of comments will not be included in
-- the definition of the procedure.
-- ================================================
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================

CREATE PROCEDURE SPKupacUpdateBrRez 
	@customerId int,
	@incr int
AS
BEGIN
	update Kupac
	set brRez = brRez + @incr
	where idKor = @customerId
END

GO

CREATE PROCEDURE SPProdavacUpdateBrApart 
	@userId int,
	@incr int
AS
BEGIN
	update Prodavac
	set brApart= brApart + @incr
	where idKor = @userId;

END

GO
CREATE PROCEDURE SPApartmanUpdateBrRez
	@apartmentId int,
	@incr int
AS
BEGIN
	update Apartman
	set brRez = brRez + @incr
	where idApar = @apartmentId
END

GO
CREATE PROCEDURE SPSobaUpdateBrRez
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
GO

CREATE PROCEDURE SPApartmanUpdateBrSoba
	@apartmentId int,
	@incr int
AS
BEGIN
	update Apartman 
	set brSoba = brSoba + @incr
	where idApar = @apartmentId;
END

GO
