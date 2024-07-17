export enum Quality {
    NONE = "-",
    ONE = "1",
    TWO = "2",
    THREE = "3",
    FOUR = "4",
    FIVE = "5"
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