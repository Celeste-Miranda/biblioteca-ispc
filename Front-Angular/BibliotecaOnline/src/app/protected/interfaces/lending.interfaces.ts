export interface LendingResponse {
    content?:          Content[];
    pageable?:         string;
    totalElements?:    number;
    totalPages?:       number;
    last?:             boolean;
    size?:             number;
    number?:           number;
    sort?:             Sort;
    numberOfElements?: number;
    first?:            boolean;
    empty?:            boolean;
}

export interface Content {
    id?:                 number;
    userAppId?:          number;
    bookId?:             number;
    dateOut?:            Date;
    dateReturn?:         Date | null;
    returnEstimateDate?: Date;
    sanctions?:          number;
    sancMoney?:          number;
}

export interface Sort {
    empty?:    boolean;
    sorted?:   boolean;
    unsorted?: boolean;
}
