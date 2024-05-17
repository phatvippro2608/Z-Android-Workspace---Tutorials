select * from Assets
select * from Departments
select * from Locations
select * from DepartmentLocations
select left(right('04/03/0001',7),2)

select top 1 *
from Assets
delete from DepartmentLocations
where ID = 14

Insert into DepartmentLocations
values ((select id from Departments where Name = 'QHSE'),(select id from Locations where Name = 'Kazan'),GETDATE(),NULL)
select top 1 ID from DepartmentLocations order by ID DESC

Insert into DepartmentLocations
values ((select id from Departments where Name = 'QHSE'),(select id from Locations where Name = 'Kazan'),GETDATE(),NULL)
Update Assets
Set 
AssetSN = '03/06/0001',
DepartmentLocationID = (select top 1 ID from DepartmentLocations order by ID DESC)
where AssetName = 'Test Asset'