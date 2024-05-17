select * from DepartmentLocations

Select * from Assets A, DepartmentLocations B, Departments C, AssetGroups D
where A.DepartmentLocationID=B.ID and B.DepartmentID=C.ID and A.AssetGroupID=D.ID
and (AssetName Like '%03/06%' or AssetSN Like '%03/06%')
--and C.Name = 'R&D'
--and D.Name ='ABC'
--and StartDate >= '2/2/2023'
--and EndDate <= '1/1/2004'
