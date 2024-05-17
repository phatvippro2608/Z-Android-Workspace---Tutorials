select AssetName, C.Name as DepartmentName, AssetSN 
from Assets A, DepartmentLocations B, Departments C
where A.DepartmentLocationID = B.ID and B.DepartmentID = C.ID
and DepartmentID in (Select ID from Departments where Name = 'R&D')
and AssetGroupID in (Select ID from AssetGroups where Name = 'Electrical')
and StartDate >= ''
and EndDate <= ''

select AssetName, C.Name as DepartmentName, AssetSN 
from Assets A, DepartmentLocations B, Departments C
where A.DepartmentLocationID = B.ID and B.DepartmentID = C.ID
and (AssetName like '%suc%' or AssetSN like '%suc%')

select * from AssetGroups

select *
from Assets A, DepartmentLocations B, Departments C
where A.DepartmentLocationID = B.ID and B.DepartmentID = C.ID