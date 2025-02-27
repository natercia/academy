export interface Rack {
    id?:number;
    serialNumber?: string;
    teamId?: number;
    status?: string;
    defaultLocation?: string;
    rackAsset?: string;
    createdAt?: Date;
    modifiedAt?: Date;
    model?:string;
    reservationTime?:number;
    deleted?:boolean
}