select AssetSN,AssetName,C.Name as DepartmentName from Assets A,DepartmentLocations B,Departments C
	where A.DepartmentLocationID=B.ID and B.DepartmentID=C.ID

select Name as DepartmentName from Departments

select Name as AssetGroupName from AssetGroups


select * from Assets

select AssetSN,AssetName,C.Name as DepartmentName from Assets A,DepartmentLocations B,Departments C
                where A.DepartmentLocationID=B.ID and B.DepartmentID=C.ID 
					and DepartmentID in (select ID from Departments where Name = 'Exploration' )

select AssetSN,AssetName,C.Name as DepartmentName from Assets A,DepartmentLocations B,Departments C
                where A.DepartmentLocationID=B.ID and B.DepartmentID=C.ID 
					and AssetGroupID in (select ID from AssetGroups where Name = 'Hydraulic' )

select AssetSN,AssetName,C.Name as DepartmentName from Assets A,DepartmentLocations B,Departments C
                where A.DepartmentLocationID=B.ID and B.DepartmentID=C.ID 
					and DepartmentID in (select ID from Departments where Name = 'Exploration' )
					and AssetGroupID in (select ID from AssetGroups where Name = 'Hydraulic' )