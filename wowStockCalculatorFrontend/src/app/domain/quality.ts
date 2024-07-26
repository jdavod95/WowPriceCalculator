export enum Quality {
    NONE = "-",
    ONE = "1",
    TWO = "2",
    THREE = "3",
    FOUR = "4",
    FIVE = "5"
}

export function getQualityKey(value: string | undefined): Quality | undefined {
    switch(value) {
        case Quality.NONE.valueOf(): return Quality.NONE;
        case Quality.ONE.valueOf(): return Quality.ONE;
        case Quality.TWO.valueOf(): return Quality.TWO;
        case Quality.THREE.valueOf(): return Quality.THREE;
        case Quality.FOUR.valueOf(): return Quality.FOUR;
        case Quality.FIVE.valueOf(): return Quality.FIVE;
        default: return undefined;
    }
}

export function mapQuality(value: string | undefined): Quality {
    if (value == null){
        return Quality.NONE;
    }
    
    let result: Quality = Quality[value as keyof typeof Quality]; 
    if (result == null){
        result = Quality.NONE;
    }
    return result;
}