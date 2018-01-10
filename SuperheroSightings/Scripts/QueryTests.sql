use SuperheroSightings;

select *
from heros
	inner join hero_organization_connections con on heros.hero_id = con.hero_id
    where heros.hero_id = 4;
    
select *
from heros
	inner join sightings on heros.hero_id = sightings.hero_id
    where heros.hero_id = 4;
    
select *
from organizations
	inner join hero_organization_connections con on organizations.org_id = con.org_id
    where con.hero_id = 3;
    
    
