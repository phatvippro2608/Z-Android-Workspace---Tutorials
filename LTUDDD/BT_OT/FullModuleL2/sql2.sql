select * from Assets
select * from DepartmentLocations
select * from Employees
select * from AssetGroups

insert into DepartmentLocations
values (Select ID)

insert into Assets
values 
(?,?,
(select top 1 ID from DepartmentLocations order by ID desc),
(select ID from Employees where LastName = ?),
(select ID from AssetGroups where Name = ?),
?,?)

insert into Assets
values 
(123,123,
(select top 1 ID from DepartmentLocations order by ID desc),
(select ID from Employees where LastName = 'Krall'),
(select ID from AssetGroups where Name = 'Hydraulic'),
'','')

delete from Assets
where id>10014