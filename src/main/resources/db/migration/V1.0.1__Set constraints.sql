ALTER TABLE writers
ADD CONSTRAINT fk_writers_regions
FOREIGN KEY (region_id) REFERENCES regions(id)
ON DELETE SET NULL ;

ALTER TABLE posts
ADD CONSTRAINT fk_posts_writers
FOREIGN KEY (writer_id) REFERENCES writers(id)
ON DELETE CASCADE ;