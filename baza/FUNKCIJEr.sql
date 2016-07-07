
CREATE FUNCTION fProdavacDohvatiRezervacije
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

