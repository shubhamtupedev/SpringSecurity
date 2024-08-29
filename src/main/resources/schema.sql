INSERT INTO mst_sys_param (
    sys_param_key,
    sys_param_value,
    sys_param_desc,
    inactive,
    created_by,
    created_date,
    dtl_id
) VALUES (
    'DEFAULT_MAXIMUM_SESSION',
    '10',
    'Default maximum session for user',
    'N',
    'SYSTEM',
    sysdate,
    1
);


INSERT INTO mst_sys_param (
    sys_param_key,
    sys_param_value,
    sys_param_desc,
    inactive,
    created_by,
    created_date,
    dtl_id
) VALUES (
    'DEFAULT_PASSWORD_EXPIRY_DAYS',
    '90',
    'Default password expiry days for user',
    'N',
    'SYSTEM',
    sysdate,
    1
);

INSERT INTO mst_sys_param (
    sys_param_key,
    sys_param_value,
    sys_param_desc,
    inactive,
    created_by,
    created_date,
    dtl_id
) VALUES (
    'DEFAULT_PASSWORD_EXPIRY_ALERT_DAYS',
    '60',
    'Default password expiry alert days',
    'N',
    'SYSTEM',
    sysdate,
    1
);
