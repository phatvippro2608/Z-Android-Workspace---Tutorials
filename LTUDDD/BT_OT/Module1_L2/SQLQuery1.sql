select * from Assets
select * from DepartmentLocations
--select * from Employees
select * from Departments
select * from AssetGroups
--select AssetName, Name, AssetSN from Assets, Departments where 
select Name, AssetName, AssetSN from Assets A, Departments B, DepartmentLocations C
	where C.DepartmentID = B.ID and A.DepartmentLocationID=C.ID 