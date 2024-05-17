select * from Employees
select * from Departments
select * from AssetGroups
select * from Assets
select* from Assets A, DepartmentLocations B, Departments C
where A.DepartmentLocationID=B.ID and B.DepartmentID=C.ID

select AssetName, C.Name as Department, AssetSN from Assets A, DepartmentLocations B, Departments C
where A.DepartmentLocationID=B.ID and B.DepartmentID=C.ID

select AssetName, C.Name as Department, AssetSN from Assets A, DepartmentLocations B, Departments C
where A.DepartmentLocationID=B.ID and B.DepartmentID=C.ID
and C.Name = 'R&D'
and AssetGroupID in (Select ID from AssetGroups where Name = 'Electrical')
and A.WarrantyDate >= '1991-01-17'
and A.WarrantyDate <= '2023-2-28'

select AssetName, C.Name as Department, AssetSN from Assets A, DepartmentLocations B, Departments C
where A.DepartmentLocationID=B.ID and B.DepartmentID=C.ID
and (AssetName like '%uc%' or AssetSN like '%uc%')

select * from Locations
select * from Employees

select * from DepartmentLocations

insert into DepartmentLocations
values ((select id from Departments where Name = 'Exploration'),(select id from Locations where Name = 'Kazan'),GETDATE(),NULL)

insert into Assets
values ('01/01/01', 'Test', (select top 1 id from DepartmentLocations order by id desc),
(select id from Employees where LastName = ''), (select id from AssetGroups where Name = ''), '', ''
)