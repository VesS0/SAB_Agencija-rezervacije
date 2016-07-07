-- ================================================
-- Template generated from Template Explorer using:
-- Create Inline Function (New Menu).SQL
--
-- Use the Specify Values for Template Parameters 
-- command (Ctrl-Shift-M) to fill in the parameter 
-- values below.
--
-- This block of comments will not be included in
-- the definition of the function.
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

ALTER TRIGGER InsertBrSoba
   ON  Soba
   AFTER INSERT
AS 
BEGIN

	Declare @apartmentId int;

	select @apartmentId = idApar
	from inserted;

	exec [dbo].[SPApartmanUpdateBrRez] @apartmentId, 1;

END

GO
alter TRIGGER DeleteBrApartman
   ON  Apartman
   AFTER DELETE
AS 
BEGIN
	Declare @sellerId int;
	declare @myCursor cursor;

	set @myCursor = cursor for 
		select idKor
		from deleted;

	
	open @myCursor;
	fetch from @myCursor 
	into @sellerId;

	while @@FETCH_STATUS = 0
	begin
		exec SPProdavacUpdateBrApart @sellerId, -1;

		fetch from @myCursor 
		into @sellerId;
	end

	close @myCursor;
	deallocate @myCursor;

END

GO
ALTER TRIGGER InsertBrApartman
   ON  Apartman
   AFTER INSERT
AS 
BEGIN
	Declare @sellerId int;
	declare @myCursor cursor;

	set @myCursor = cursor for 
		select idKor
		from inserted;

	
	open @myCursor;
	fetch from @myCursor 
	into @sellerId;

	while @@FETCH_STATUS = 0
	begin
		exec SPProdavacUpdateBrApart @sellerId, 1;

		fetch from @myCursor 
		into @sellerId;
	end

	close @myCursor;
	deallocate @myCursor;
	

END

GO
CREATE TRIGGER DeleteBrRezervacija
   ON  Rezervacija
   AFTER DELETE
AS 
BEGIN
	Declare @customerId int;
	Declare @apartmentId int;
	Declare @roomNo int;
	declare @myCursor cursor;

	set @myCursor = cursor for 
		select d.idKor, S.idApar, S.rb 
		from deleted d, Soba S
		where D.idSob=S.idSob AND DatumDo < getdate();

	
	open @myCursor;
	fetch from @myCursor 
	into @customerId, @apartmentId, @roomNo;

	while @@FETCH_STATUS = 0
	begin
		exec SPKupacUpdateBrRez @customerId, 1;
		exec SPApartmanUpdateBrRez @apartmentId, 1;
		exec SPSobaUpdateBrRez @apartmentId, @roomNo, 1;

		update fProdavacDohvatiRezervacije(@apartmentId)
		set brRez = brRez + 1;

		fetch from @myCursor 
		into @customerId,@apartmentId,@roomNo ;
	end

	close @myCursor;
	deallocate @myCursor;
	

END

GO

CREATE TRIGGER DeleteBrSoba 
   ON  Soba
   AFTER DELETE
AS 
BEGIN

	Declare @apartmentId int;

	select @apartmentId = idApar
	from deleted;

	exec SPApartmanUpdateBrSoba @apartmentId, -1;

END
GO