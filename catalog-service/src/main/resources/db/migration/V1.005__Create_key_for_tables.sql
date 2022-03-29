alter table if exists category_aud add constraint FKc9m640crhsib2ws80um6xuk1w foreign key (rev) references revinfo;
alter table if exists product add constraint FK1mtsbur82frn64de7balymq9s foreign key (category_id) references category;
alter table if exists product_aud add constraint FK9vwllld6jlw5xys1ay911oh1x foreign key (rev) references revinfo;
