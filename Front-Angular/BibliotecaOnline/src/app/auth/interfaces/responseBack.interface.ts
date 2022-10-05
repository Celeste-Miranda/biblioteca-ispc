export interface ResponseBack {
    headers?:    Headers;
    status?:     number;
    statusText?: string;
    url?:        string;
    ok?:         boolean;
    name?:       string;
    message?:    string;
    error?:      Error;
}

export interface Error {
    status:    number;
    message:   string;
    timestamp: Date;
    errors:    null;
}
